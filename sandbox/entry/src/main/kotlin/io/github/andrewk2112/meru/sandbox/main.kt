package io.github.andrewk2112.meru.sandbox

import org.jetbrains.annotations.NotNull

@SampleAnnotation
private const val BAZ = 456

@NotNull
private const val BAR = 789

fun main() {
    FOO // no reaction for cases when it's declared in another module 🥲
    BAZ
    BAR
}
