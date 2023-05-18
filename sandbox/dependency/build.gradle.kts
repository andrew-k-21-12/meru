plugins {
    alias(dslLibs.plugins.kotlin.jvm)
    // Even while this module is used as a dependency for the one having the compiler plugin applied,
    // it's still required to enable the compiler plugin here if it's needed to have its sources processed as well:
    // compiler plugins are limited to modules they applied to and don't process other sources transitively.
    id("io.github.andrew-k-21-12.meru")
}

meru {
    targetAnnotationClass.set("org.jetbrains.annotations.NotNull")
}
