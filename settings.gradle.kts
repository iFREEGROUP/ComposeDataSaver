pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://plugins.gradle.org/m2/")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    enableFeaturePreview("VERSION_CATALOGS")
    repositories {
        mavenCentral()
        maven("https://repo1.maven.org/maven2/")
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/google/")
        maven("https://maven.aliyun.com/repository/jcenter/")
        maven("https://jitpack.io")
        maven("https://maven.google.com/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

rootProject.name = "ComposeDataSaver"
include(":composeApp")
include(":data_saver_core")
include(":data_saver_mmkv")
include(":data_saver_data_store_preferences")