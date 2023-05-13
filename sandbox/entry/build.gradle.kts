plugins {
    alias(dslLibs.plugins.kotlin.jvm)
    id("io.github.andrew-k-21-12.meru")
}

dependencies {
    implementation(projects.dependency)
}
