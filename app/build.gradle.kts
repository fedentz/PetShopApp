plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.hilt)

    id("com.google.gms.google-services")
}

android {
    namespace   = "com.fedenintzel.petshopapp"
    compileSdk  = 35

    defaultConfig {
        applicationId   = "com.fedenintzel.petshopapp"
        minSdk          = 26
        targetSdk       = 35
        versionCode     = 1
        versionName     = "1.0"
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
    kotlinOptions {
        // Como tu compileOptions está en 17, conviene que el jvmTarget sea 17 también:
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // — Retrofit & OkHttp —
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // — Lifecycle / ViewModel / Coroutines —
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // — Hilt —
    implementation(libs.hilt.android)
    implementation(libs.firebase.auth.ktx)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // — Room (si lo usas) —
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    // — Coroutines explícitas —
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // — Navigation Compose —
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // — Aquí va la librería que faltaba para poder usar collectAsState() sobre StateFlow<T> —
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    // (o, si usas Version Catalog y tienes alias, podría ser:
    //    implementation(libs.androidx.lifecycle.runtime.compose)
    // )

    // — Coil (para cargar imágenes) —
    implementation(libs.coil.compose)

    // — AndroidX básico & Compose —
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // — Tests —
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
