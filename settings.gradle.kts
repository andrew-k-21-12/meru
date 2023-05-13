@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        // At the moment when some versions common for multiple scopes appear,
        // it's possible to extract them into a new separate catalog for dependencies with shared versions
        // or group them by dependencies' providers: Kotlin, Google, etc.
        create("dslLibs") {
            from(files("dependencies/dsl.toml"))
        }
        create("mainLibs") {
            from(files("dependencies/main.toml"))
        }
        create("testLibs") {
            from(files("dependencies/test.toml"))
        }
    }
}

rootProject.name = "meru"
include("compiler-plugin", "gradle-plugin")
