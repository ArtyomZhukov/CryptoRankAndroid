plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
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
    }
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(project(":shared:core"))

    //Cache
    implementation("androidx.room:room-runtime:2.3.0")
    implementation("androidx.room:room-ktx:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")

    //Network
    implementation("io.ktor:ktor-client-core:1.5.3")
    implementation("io.ktor:ktor-client-json:1.5.3")
    implementation("io.ktor:ktor-client-logging:1.5.3")
    implementation("io.ktor:ktor-client-serialization:1.5.3")
    implementation("io.ktor:ktor-client-android:1.5.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}