package io.github.andrewk2112.meru

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.*
import org.jetbrains.kotlin.config.CompilerConfiguration

/**
 * Describes how to match options provided from outside
 * (command line, Gradle extension of the corresponding Gradle plugin)
 * to internal configurations of the compiler plugin.
 *
 * See [MeruCompilerPluginRegistrar] for details on the [AutoService] annotation.
 */
@Suppress("unused")
@ExperimentalCompilerApi
@AutoService(CommandLineProcessor::class)
internal class MeruCommandLineProcessor : CommandLineProcessor {

    /**
     * Matches external string options to internal type-safe compiler plugin's configurations.
     */
    @Throws(CliOptionProcessingException::class)
    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) =
        when (option.optionName) {
            TARGET_ANNOTATION_CLASS_OPTION -> configuration.put(TARGET_ANNOTATION_CLASS_CONFIG_KEY, value)
            else                           -> throw CliOptionProcessingException(
                                                  "Unsupported configuration option provided: ${option.optionName}"
                                              )
        }

    /**
     * Won't compile when pointed to a wrong identifier.
     */
    override val pluginId: String = "compiler-plugin"

    /**
     * The declaration of all available compiler plugin options with their descriptions.
     */
    override val pluginOptions: Collection<CliOption> = listOf(
        CliOption(
            optionName = TARGET_ANNOTATION_CLASS_OPTION,
            valueDescription = "String",
            description = "A fully qualified target annotation class name to be looked for",
            required = true,
            allowMultipleOccurrences = false
        ),
    )

}
