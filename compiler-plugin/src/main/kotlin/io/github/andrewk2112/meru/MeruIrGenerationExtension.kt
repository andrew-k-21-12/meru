package io.github.andrewk2112.meru

import io.github.andrewk2112.meru.visitors.MeruIrElementTransformerVoid
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.util.IrMessageLogger
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid

/**
 * Just one of extensions contributing to the compilation. Passes the [messageLogger] to further processing.
 */
internal class MeruIrGenerationExtension(private val messageLogger: IrMessageLogger) : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) =
        moduleFragment.transformChildrenVoid(MeruIrElementTransformerVoid(messageLogger))

}
