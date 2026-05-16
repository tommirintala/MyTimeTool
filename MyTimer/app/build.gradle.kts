

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    // id("com.google.devtools.ksp")
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)

}

android {
    namespace = "fi.fimurito.mytimer"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "fi.fimurito.mytimer"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"




    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }

    /*
    dependenciesInfo {
        // Disables dependency metadata when building APK's
        includeInApk = false
        // Disables dependency metadata when building Android App Bundles
        includeInBundle = false
    }
     */

    //kotlinOptions {
    //    freeCompilerArgs = listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    //}

    testOptions {
        packaging {
            //resources.excludes.add("META-INF/*")
            resources.excludes.add(("META-INF/LICENSE.md"))
            resources.excludes.add(("META-INF/LICENSE-notice.md"))
        }
    }

}

room {
    schemaDirectory("$projectDir/schemas")
}


dependencies {



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)
    //implementation(libs.org.jetbrains.kotlin.plugin.serialization.gradle.plugin)
    implementation(libs.kotlinx.serialization.json)
    //implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.robolectric)
    //implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.sqlite.bundled)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)




    testImplementation(libs.kotlintest.core)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.room.compiler)
    //testImplementation(libs.androidx.room.testing)
    testImplementation(libs.mockito)

    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.room.compiler)
    androidTestImplementation(libs.mockito.android)

    debugImplementation(libs.turbine)
    debugImplementation(libs.junit)
    debugImplementation(libs.junit.jupiter)
    debugImplementation(libs.mockito.kotlin)
    debugImplementation(libs.kotlinx.coroutines.test)
    debugImplementation(libs.robolectric)
    debugImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.espresso.core)
    debugImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.room.compiler)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    annotationProcessor(libs.androidx.room.compiler)
    // // ksp(libs.androidx.room.compiler)
    //annotationProcessor("androidx.room:room-compiler:$roomVersion")
    //ksp("androidx.room:room-compiler:$roomVersion")
    ksp(libs.androidx.room.compiler)

    configurations.all {
        exclude(group = "com.intellij", module="annotations")
    }

}