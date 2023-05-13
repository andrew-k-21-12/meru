plugins {
    alias(dslLibs.plugins.kotlin.jvm) // required to compile the plugin and use it by including locally
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        create("meru") {
            id                  = "io.github.andrew-k-21-12.meru"
            implementationClass = "io.github.andrewk2112.meru.MeruPlugin"
        }
    }
}

dependencies {
    // Required to create KotlinCompilerPluginSupportPlugin implementations.
    implementation(mainLibs.kotlin.gradleplugin.api)
}
