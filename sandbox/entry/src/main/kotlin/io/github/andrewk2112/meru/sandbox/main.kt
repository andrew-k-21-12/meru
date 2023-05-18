package io.github.andrewk2112.meru.sandbox

import org.jetbrains.annotations.NotNull

private fun main() {
    Dependency.FOO // no reaction for cases when it's declared in another module 🥲
    Wrapper.BAZ
    BAR
    NOT_ANNOTATED
}

@NotNull
private const val BAR = 789

private const val NOT_ANNOTATED = 0
