plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.faztbit.alwaopportunity"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.faztbit.alwaopportunity"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation("io.coil-kt:coil:2.5.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

    implementation("androidx.navigation:navigation-fragment-ktx:$2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:$2.5.3")


    implementation("io.insert-koin:koin-core:3.2.1")
    implementation("io.insert-koin:koin-android:3.2.1")
    implementation("io.coil-kt:coil:2.5.0")

    implementation("io.data2viz.charts:core:1.1.0-eap1")
    implementation("io.data2viz.d2v:viz:0.8.12")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}