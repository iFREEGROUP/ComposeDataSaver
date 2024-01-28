import com.google.gson.internal.JavaVersion
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
//    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.jetbrainsCompose)
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("com.android.application")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xmulti-platform", "-Xexpect-actual-classes")
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(project(":data_saver_mmkv"))
            implementation(project(":data_saver_data_store_preferences"))

            /**
             * implementation project(path: ":data_saver_mmkv")
             *     implementation 'com.tencent:mmkv:1.2.14'
             *
             *     // if you want to use DataStore
             *     implementation project(path: ':data_saver_data_store_preferences')
             *     def data_store_version = "1.0.0"
             *     implementation "androidx.datastore:datastore:$data_store_version"
             *     implementation "androidx.datastore:datastore-preferences:$data_store_version"
             */
            implementation(libs.mmkv)
            implementation(libs.datastore)
            implementation(libs.datastore.preferences)

            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {

            implementation(project(":data_saver_core"))
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.serialization.json)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.funny.data_saver"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.funny.data_saver"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.funny.data_saver"
            packageVersion = "1.0.0"
        }
    }
}
