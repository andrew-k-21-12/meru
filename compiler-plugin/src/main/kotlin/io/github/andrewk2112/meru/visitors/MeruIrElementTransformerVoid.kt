package io.github.andrewk2112.meru.visitors

import org.jetbrains.kotlin.backend.jvm.codegen.AnnotationCodegen.Companion.annotationClass
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrFunctionImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrPropertyImpl
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

/**
 * A visitor to perform some processing intended to be done by the compiler plugin.
 *
 * Please keep in mind that it visits only the code from those Gradle modules
 * for which this compiler plugin was enabled:
 * no declarations from dependency Gradle modules are going to be visited transitively
 * until the compiler plugin is applied to them explicitly.
 *
 * At the same time it's possible to get some basic information for references
 * which are declared outside in other dependency Gradle modules
 * (for example, fully qualified names of their declarations can be retrieved).
 *
 * All type casts and other manipulations done below were inferred by debugging-based reverse engineering,
 * as no documentations were found for these operations.
 */
internal class MeruIrElementTransformerVoid(
    private val targetAnnotationClass: String,
    private val messageLogger: IrMessageLogger,
) : IrElementTransformerVoid() {

    // Overrides.

    /**
     * Getting here on each encountering of declaration or reference within the code being compiled.
     */
    override fun visitDeclarationReference(expression: IrDeclarationReference): IrExpression {

        // Checking if it is a property declaration and reporting whether it has the target annotation.
        (expression.symbol.owner as? IrField)?.let { field ->
            field.annotations
                .find { it.annotationClass.kotlinFqName.asString() == targetAnnotationClass }
                ?.let {
                    report("Found a declaration `${field.kotlinFqName}` annotated with the target annotation")
                }
        }

        // Getting the declaration of a reference (if present) and reporting whether it has the target annotation.
        (expression.symbol.owner as? IrFunctionImpl)?.correspondingPropertySymbol?.let { propertyDeclaration ->
            (propertyDeclaration.owner as? IrPropertyImpl)?.backingField?.annotations
                ?.find { it.annotationClass.kotlinFqName.asString() == targetAnnotationClass }
                ?.let {
                    report(
                        "Found a reference to `${propertyDeclaration.owner.fqNameWhenAvailable}` " +
                                "which declaration is annotated with the target annotation",
                    )
                }
        }

        return super.visitDeclarationReference(expression)

    }



    // Private.

    /**
     * Just a short form to do all similar reporting here.
     */
    private fun report(message: String) = messageLogger.report(IrMessageLogger.Severity.WARNING, message, null)

}
