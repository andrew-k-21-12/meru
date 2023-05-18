package io.github.andrewk2112.meru

import org.jetbrains.kotlin.config.CompilerConfigurationKey

// This file contains one example configuration option for the compiler plugin.

/**
 * This option name is used to set a fully qualified target annotation class name
 * to be looked for by the compiler plugin.
 *
 * The option can be set by command line or Gradle extension.
 */
internal const val TARGET_ANNOTATION_CLASS_OPTION = "targetAnnotationClass"

/**
 * This is a type-bound key for internal configuration of the compiler plugin.
 *
 * Using the same [TARGET_ANNOTATION_CLASS_OPTION] key for it for convenience.
 */
internal val TARGET_ANNOTATION_CLASS_CONFIG_KEY = CompilerConfigurationKey<String>(TARGET_ANNOTATION_CLASS_OPTION)
