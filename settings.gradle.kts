pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GettingStartWithCompose"
include(":app")
include(":features:login")
include(":features:dashboard")
include(":features:explore")
include(":core:domain")
include(":features:quotes")
include(":core:network")
include(":core:common")
include(":core:data")
include(":features:tweets")
