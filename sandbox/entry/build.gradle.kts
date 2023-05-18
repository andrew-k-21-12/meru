plugins {
    alias(dslLibs.plugins.kotlin.jvm)
    id("io.github.andrew-k-21-12.meru")
}

meru {
    targetAnnotationClass.set("org.jetbrains.annotations.NotNull")
}

dependencies {
    // The module used as a dependency here won't be processed by the compiler plugin
    // until it's applied to it explicitly as well.
    // It seems like there are some limitations in accessing declarations from dependency modules:
    // could not have any luck in retrieving annotations for such declarations, for example.
    implementation(projects.dependency)
}
