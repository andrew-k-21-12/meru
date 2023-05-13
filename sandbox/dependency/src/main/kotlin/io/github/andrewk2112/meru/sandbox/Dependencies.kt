package io.github.andrewk2112.meru.sandbox

import org.jetbrains.annotations.NotNull

@Target(AnnotationTarget.FIELD)
annotation class SampleAnnotation()

@NotNull
const val FOO = 123
