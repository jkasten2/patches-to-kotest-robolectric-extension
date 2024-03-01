plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.patched_kotest_robolectric_extension"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")

    implementation("junit:junit:4.13.2")

    implementation("io.kotest:kotest-runner-junit5:5.8.0")
    implementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")

    implementation("org.robolectric:robolectric:4.11.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
}