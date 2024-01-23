plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.vishalpvijayan.smyttens"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vishalpvijayan.smyttens"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
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
}

dependencies {

    val coroutines = ("1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val coil = ("2.5.0")
    implementation("io.coil-kt:coil:$coil")

    val viewmodel = ("2.7.0")
    val activity = ("1.8.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewmodel")
    implementation ("androidx.activity:activity-ktx:$activity")

    val navigation = ("2.7.6")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation")

    // Room
    val room = ("2.6.1")
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    implementation("androidx.hilt:hilt-common:1.1.0")
    ksp("androidx.room:room-compiler:$room")

    // Dagger-Hilt
    val hilt = ("2.48.1")
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-compiler:$hilt")
    ksp ("com.google.dagger:hilt-android-compiler:$hilt")

    val corektx = ("1.12.0")
    val appcompat = ("1.6.1")
    val material = ("1.11.0")
    val constraintlayout = ("2.1.4")

    implementation("androidx.core:core-ktx:$corektx")
    implementation("androidx.appcompat:appcompat:$appcompat")
    implementation("com.google.android.material:material:$material")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayout")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}