package io.github.andrewk2112.meru

import com.tschuchort.compiletesting.KotlinCompilation
import org.junit.jupiter.api.Assertions.assertEquals
import org.opentest4j.AssertionFailedError
import kotlin.jvm.Throws

/**
 * Just asserts if the [KotlinCompilation.Result] is successful and passes it further.
 */
@Throws(AssertionFailedError::class)
internal fun KotlinCompilation.Result.assertCompiled(): KotlinCompilation.Result = apply {
    assertEquals(KotlinCompilation.ExitCode.OK, exitCode)
}
