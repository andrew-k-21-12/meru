plugins {
    alias(dslLibs.plugins.kotlin.jvm)
    alias(dslLibs.plugins.kotlin.kapt)
    // Without having this plugin applied the compiler plugin won't be triggered to be compiled by sandbox projects
    // and won't be located by them locally.
    id("com.vanniktech.maven.publish")
}

tasks {
    test { useJUnitPlatform() }
}

dependencies {

    kapt(mainLibs.google.autoservice.core)
    compileOnly(mainLibs.google.autoservice.annotations)
    compileOnly(mainLibs.kotlin.compiler.embedable)

    testImplementation(testLibs.tschuchortdev.kotlincompiletesting)
    testImplementation(platform(testLibs.junit.bom))
    testImplementation(testLibs.junit.jupiter)

}
