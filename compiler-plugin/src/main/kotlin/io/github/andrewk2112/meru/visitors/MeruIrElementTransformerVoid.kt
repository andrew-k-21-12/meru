package io.github.andrewk2112.meru.visitors

import org.jetbrains.kotlin.backend.jvm.codegen.AnnotationCodegen.Companion.annotationClass
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrFunctionImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrPropertyImpl
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.FqName

/**
 * A visitor to perform some sample processing done by the compiler plugin.
 */
internal class MeruIrElementTransformerVoid(private val messageLogger: IrMessageLogger) : IrElementTransformerVoid() {

    // Overrides.

    /**
     * Visits both declarations and further references to them.
     */
    override fun visitDeclarationReference(expression: IrDeclarationReference): IrExpression {

        // Fetching and reporting fully qualified names of all annotations used for a declaration (if any).
        expression.annotations?.map { it.fqName }?.joinToString()?.let {
            messageLogger.report(
                IrMessageLogger.Severity.WARNING,
                "Say hello to a declaration annotated with: $it",
                null
            )
        }

        // Getting the declaration (if present) of a reference and reporting its annotations' fully qualified names.
        expression.declaration?.let { declaration ->
            messageLogger.report(
                IrMessageLogger.Severity.WARNING,
                "Here comes a reference to `${declaration.fqName}` " +
                        "annotated with: ${declaration.annotations?.map { it.fqName }?.joinToString()}",
                null
            )
        }

        return super.visitDeclarationReference(expression)

    }



    // Private - a bit of magic done by debugging-based reverse engineering.

    private val IrDeclarationReference.annotations: List<IrConstructorCall>?
        get() = (symbol.owner as? IrField)?.annotations

    private val IrConstructorCall.fqName: FqName
        get() = annotationClass.kotlinFqName

    private val IrDeclarationReference.declaration: IrPropertySymbol?
        get() = (symbol.owner as? IrFunctionImpl)?.correspondingPropertySymbol

    private val IrPropertySymbol.fqName: FqName?
        get() = (owner as? IrPropertyImpl)?.fqNameWhenAvailable

    private val IrPropertySymbol.annotations: List<IrConstructorCall>?
        get() = (owner as? IrPropertyImpl)?.backingField?.annotations

}
