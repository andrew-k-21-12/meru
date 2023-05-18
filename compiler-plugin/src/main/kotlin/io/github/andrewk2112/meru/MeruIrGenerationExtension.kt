package io.github.andrewk2112.meru

import io.github.andrewk2112.meru.visitors.MeruIrElementTransformerVoid
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.util.IrMessageLogger
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid

/**
 * An extension contributing to the compilation.
 * Passes the [targetAnnotationClass] and [messageLogger] for further processing.
 */
internal class MeruIrGenerationExtension(
    private val targetAnnotationClass: String,
    private val messageLogger: IrMessageLogger,
) : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        moduleFragment.transformChildrenVoid(MeruIrElementTransformerVoid(targetAnnotationClass, messageLogger))
        // At this point the transformer above completes all its processing
        // (having the entire Gradle module's codebase visited),
        // so it can be a good place to apply some post-processing here accordingly.
    }

}
