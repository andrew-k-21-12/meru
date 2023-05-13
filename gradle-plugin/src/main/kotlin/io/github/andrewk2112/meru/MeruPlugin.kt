package io.github.andrewk2112.meru

import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

/**
 * This is a Gradle accessor for the Kotlin compiler plugin:
 * it's used to include the compiler plugin for compilation and configure it.
 *
 * See the [KotlinCompilerPluginSupportPlugin]'s docs for other details.
 */
@Suppress("unused")
private class MeruPlugin : KotlinCompilerPluginSupportPlugin {

    /**
     * This is a good place to enable or disable the compiler plugin
     * according to some environment conditions for example.
     */
    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true

    /**
     * This is a place to configure the compiler plugin:
     * it can pass some Gradle-set configurations to the compiler plugin itself
     * or configure the compilation targets (Gradle modules) accordingly
     * (for example by setting some additional required dependencies for them).
     *
     * See the original docs for [KotlinCompilerPluginSupportPlugin.applyToCompilation] as well.
     */
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> =
        kotlinCompilation.target.project.provider { emptyList() }

    override fun getCompilerPluginId(): String = getPluginArtifact().artifactId

    /**
     * Sets a lookup for the required Kotlin compiler plugin
     * which is going to be involved when this Gradle plugin is applied.
     *
     * Make sure to provide correct artifact descriptions to find the assembled JAR (artifact) of the compiler plugin.
     * The compiler plugin should be assembled and published somewhere (local publishing is fine too)
     * to be found by this Gradle plugin.
     */
    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        "io.github.andrew-k-21-12.meru",
        "compiler-plugin",
        "1.0.0"
    )

}
