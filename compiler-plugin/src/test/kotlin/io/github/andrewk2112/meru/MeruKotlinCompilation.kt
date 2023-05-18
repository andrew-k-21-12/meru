package io.github.andrewk2112.meru

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.intellij.lang.annotations.Language
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.incremental.createDirectory
import org.jetbrains.kotlin.incremental.deleteDirectoryContents
import java.io.File
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Creates an instance of [KotlinCompilation] with a bunch of default reusable configs
 * to make [MeruCompilerPluginRegistrar] involved into the compilation.
 */
@ExperimentalCompilerApi
@Throws(IllegalStateException::class, IOException::class, SecurityException::class)
internal fun createMeruKotlinCompilation(targetAnnotationClass: String) = KotlinCompilation().apply {
    compilerPluginRegistrars = listOf(MeruCompilerPluginRegistrar(targetAnnotationClass))
    inheritClassPath         = true
    messageOutputStream      = System.out
    useIR                    = true
    verbose                  = false
    workingDir               = File("build", "test-kotlin-compilation").apply {
                                   createDirectory()
                                   deleteDirectoryContents()
                               }
}

/**
 * Compiles the [source] block of Kotlin code with saving it into the [outFileName].
 *
 * Has correct syntax highlighting for the [source] code within the IDE.
 */
internal fun KotlinCompilation.compile(
    @Language("kotlin") source: String,
    outFileName: String = "Main.kt"
): KotlinCompilation.Result {
    sources = listOf(SourceFile.kotlin(outFileName, source, true))
    return compile()
}
