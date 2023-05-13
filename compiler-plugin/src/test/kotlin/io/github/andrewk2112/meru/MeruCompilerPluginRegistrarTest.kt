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

private fun bar() {
    FOO
}
            """)
            .assertCompiled()
    }



    // Private.

    @ExperimentalCompilerApi
    private val compiler: KotlinCompilation = createMeruKotlinCompilation()

}
