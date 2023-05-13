package io.github.andrewk2112.meru

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.ir.util.irMessageLogger
import java.util.ServiceLoader

/**
 * This is an entry point for the Kotlin compiler plugin itself.
 *
 * It's super important for this class to be marked as a service for [CompilerPluginRegistrar] -
 * in this case it's done by the [AutoService] annotation.
 * Otherwise, metadata required to make this plugin observable and loadable by [ServiceLoader]
 * (which is used under the hood to load Kotlin compiler plugins)
 * won't be generated, and all compilations will ignore the existence of this plugin without any reporting at all.
 *
 * Read more about [ServiceLoader] and [AutoService] to get understanding about how they work.
 */
@ExperimentalCompilerApi
@AutoService(CompilerPluginRegistrar::class)
internal class MeruCompilerPluginRegistrar : CompilerPluginRegistrar() {

    /**
     * All extensions contributing to the compilation are getting registered here.
     */
    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) =
        IrGenerationExtension.registerExtension(MeruIrGenerationExtension(configuration.irMessageLogger))

    override val supportsK2 = true

}
