@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("dslLibs") {
            from(files("../dependencies/dsl.toml"))
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// This way of including external builds (for example the ones with plugins)
// is not recommended as it does not support all use cases and might be deprecated in a future Gradle version.
// However, in this particular case it's more convenient rather than putting it within the pluginManagement section,
// because otherwise the mechanism of tying a Kotlin compiler plugin with its Gradle plugin
// (handled by the com.vanniktech.maven.publish plugin) won't work.
includeBuild("../")
rootProject.name = "sandbox"
include("dependency", "entry")
