import java.io.File

var cachedVersion: Int? = null

fun getIncrementalVersion(): Int {
    if (cachedVersion != null) {
        return cachedVersion!!
    }
    val versionFile = File("${project.rootDir}/version.txt")

    if (!versionFile.exists() || versionFile.readText().trim().isEmpty()) {
        versionFile.writeText("1")
    }

    val currentVersion = try {
        versionFile.readText().trim().toInt()
    } catch (e: NumberFormatException) {
        versionFile.writeText("1")
        1
    }
    versionFile.writeText((currentVersion + 1).toString())

    cachedVersion = currentVersion
    return currentVersion
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.ecommercesample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ecommercesample"
        minSdk = 29
        targetSdk = 35
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

    kapt {
        correctErrorTypes = true
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.all {
            val apkOutput = this as com.android.build.gradle.internal.api.ApkVariantOutputImpl
            val newName = "EcommerceSample-${variant.name}-V${getIncrementalVersion()}.apk"
            apkOutput.outputFileName = newName
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

    implementation (libs.retrofit)
    implementation (libs.gson)
    implementation (libs.gson.converter)
    implementation(libs.coil.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.viewmodel.compose)
    implementation (libs.valentinilk.shimmer)
    implementation (libs.runtime.ktx)
    implementation (libs.hilt.navigation.compose)
    implementation (libs.okhttp.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}