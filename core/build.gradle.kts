plugins {
    id(Plugin.Android.library)
    id(Plugin.Jetbrains.kotlin)
    kotlin(Plugin.Kotlin.kapt)
}

apply {
    from("$rootDir/ktlint.gradle")
}

android {
    namespace = Modules.Core.namespace
    compileSdk = Modules.Core.compileSdk

    defaultConfig {
        minSdk = Modules.Core.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)
    testImplementation(Dependencies.Test.junit)
}