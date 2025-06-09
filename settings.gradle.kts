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
    plugins {
        id("com.google.dagger.hilt.android") version "2.51"
    }
}
//buildscript {
//    repositories {
//        google()           // ✅ Required for Hilt
//        mavenCentral()
//    }
//    dependencies {
//        classpath("com.android.tools.build:gradle:8.2.2")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48") // ✅ Hilt plugin
//    }
//}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyNewsApp"
include(":app")
