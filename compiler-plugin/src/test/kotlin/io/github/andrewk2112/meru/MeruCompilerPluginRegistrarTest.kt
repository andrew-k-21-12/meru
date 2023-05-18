package io.github.andrewk2112.meru

import com.tschuchort.compiletesting.KotlinCompilation
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.junit.jupiter.api.Test

private class MeruCompilerPluginRegistrarTest {

    // Tests.

    @Test @ExperimentalCompilerApi
    fun compiles() {
        compiler
            .compile("""
import org.jetbrains.annotations.NotNull

@NotNull  
private const val FOO = 123
private const val BAR = 456

private fun bar() {
    FOO
    BAR
}
            """)
            .assertCompiled()
    }



    // Private.

    @ExperimentalCompilerApi
    private val compiler: KotlinCompilation = createMeruKotlinCompilation("org.jetbrains.annotations.NotNull")

}
