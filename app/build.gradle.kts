plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.aditys.h_news"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aditys.h_news"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.core.splashscreen) //Splash Api
    implementation( libs.retrofit) //Retrofit
    implementation(libs.converter.gson)
    implementation(libs.coil.compose) //Coil
    implementation( libs.androidx.datastore.preferences) //Datastore
    implementation (libs.androidx.foundation) //Compose Foundation
    implementation (libs.accompanist.systemuicontroller) //Accompanist
    implementation (libs.paging.runtime) //Paging 3
    implementation( libs.androidx.paging.compose)
    implementation(libs.hilt.android) //hilt
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose.v100)
    implementation (libs.androidx.room.room.runtime) //Room
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation (libs.androidx.room.ktx.v252)
    implementation (libs.navigation.compose) //Compose Navigation
    implementation (libs.kotlinx.coroutines.android)  // Coroutines for asynchronous programming
    

}