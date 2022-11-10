plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.muzz.chatty"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.muzz.chatty"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    ksp {
        arg("room.schemaLocation", "$rootDir/schemas") // Location of the database schema
        arg("room.incremental", "true") // Enables Gradle incremental annotation processor
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.bundles.androidx.compose)

    // Hilt
    implementation(libs.androidx.hilt.core)
    kapt(libs.androidx.hilt.kapt)

    // Room
    implementation(libs.bundles.androidx.room)
    ksp(libs.androidx.room.ksp)
}