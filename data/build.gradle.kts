import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugin.Android.library)
    id(Plugin.Jetbrains.kotlin)
    id(Plugin.Hilt.android)
    kotlin(Plugin.Kotlin.kapt)
}

apply {
    from("$rootDir/ktlint.gradle")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    val apiKey: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        all {
            buildConfigField("String", "API_KEY", apiKey)
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    kapt {
        javacOptions {
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(Modules.Domain.name))
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.SquareUp.okhttp)
    implementation(Dependencies.SquareUp.okhttpLoggingInterceptor)
    implementation(Dependencies.SquareUp.retrofit)
    implementation(Dependencies.SquareUp.retrofitConverterGson)
    implementation(Dependencies.Libraries.networkResponseAdapter)
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Hilt.hiltPlugin)
    api(Dependencies.Google.gson)
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.coroutinesTesting)
}