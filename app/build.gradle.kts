plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.devtools.ksp")

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    implementation(libs.hilt.android.v244)
    kapt(libs.hilt.android.compiler)

    implementation(libs.opencsv)
    val room_version = "2.6.1"

    implementation(libs.room)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation (libs.retrofit)
    //Gson -> json data to java or kotlin format
    implementation (libs.converter.gson)
    //coroutines -> https://github.com/Kotlin/kotlinx.coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android.v172)
    //Viewmodel and livedata -> https://developer.android.com/jetpack/androidx/releases/lifecycle
    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Annotation processor
    kapt(libs.androidx.lifecycle.compiler)
    // login intercept
    implementation(libs.logging.interceptor)

    implementation(libs.moshi)
    implementation(libs.retrofitConverterWire)

    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation ("io.github.raamcosta.compose-destinations:core:1.1.2-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.1.2-beta")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}