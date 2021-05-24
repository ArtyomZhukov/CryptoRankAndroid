plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 21
        targetSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.32"
    }
}

dependencies {
    api("androidx.core:core-ktx:1.5.0")
    api("androidx.appcompat:appcompat:1.3.0")
    api("com.google.android.material:material:1.3.0")

    //Jetpack Compose
    api("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    api("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    api("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    api("androidx.compose.runtime:runtime-livedata:${rootProject.extra["compose_version"]}")


    //Navigation
    api("androidx.navigation:navigation-ui-ktx:2.3.5")
    api("androidx.navigation:navigation-compose:2.4.0-alpha01")

    //UI - Compose - Accompanist
    api("com.google.accompanist:accompanist-swiperefresh:0.8.1")

    api("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    //DI-Compose
    api("io.insert-koin:koin-android:3.0.2")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}