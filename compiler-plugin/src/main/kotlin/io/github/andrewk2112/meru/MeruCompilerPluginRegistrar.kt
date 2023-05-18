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
 *
 * @param targetAnnotationClass A fully qualified target annotation class name to look for (an example of configuration).
 *                              Extracted as a constructor argument here only to simplify testing,
 *                              check [MeruCommandLineProcessor] to see how to properly declare configuration options.
 *                              Must be not empty, or [IllegalArgumentException] will be thrown.
 */
@ExperimentalCompilerApi
@AutoService(CompilerPluginRegistrar::class)
internal class MeruCompilerPluginRegistrar(private val targetAnnotationClass: String = "") : CompilerPluginRegistrar() {

    /**
     * Extracting and processing the [CompilerConfiguration] set by the [MeruCommandLineProcessor],
     * registering all extensions contributing to the compilation.
     */
    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        val targetAnnotationClass = configuration
            .get(TARGET_ANNOTATION_CLASS_CONFIG_KEY, targetAnnotationClass)
            .takeIf { it.isNotBlank() } ?: throw IllegalArgumentException("No target annotation class name is provided")
        IrGenerationExtension.registerExtension(
            MeruIrGenerationExtension(targetAnnotationClass, configuration.irMessageLogger)
        )
    }

    override val supportsK2 = true

}
