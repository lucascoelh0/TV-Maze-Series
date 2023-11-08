object Dependencies {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleRuntimeKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
        const val splashScreen = "androidx.core:core-splashscreen:${Version.splashScreen}"
    }

    object Compose {
        const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
        const val ui = "androidx.compose.ui:ui:${Version.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
        const val material3 = "androidx.compose.material3:material3:${Version.composeMaterial3}"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Version.compose}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.lifecycleRuntimeComposeVersion}"
        const val themeAdapter =
            "com.google.android.material:compose-theme-adapter:${Version.composeThemeAdapterVersion}"
        const val foundation = "androidx.compose.foundation:foundation:${Version.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Version.compose}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.viewModelCompose}"
        const val coilCompose = "io.coil-kt:coil-compose:${Version.coil}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Version.material}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationCompose}"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
    }

    object Libraries {
        const val composeShimmer = "com.valentinilk.shimmer:compose-shimmer:${Version.composeShimmer}"
        const val networkResponseAdapter =
            "com.github.haroldadmin:NetworkResponseAdapter:${Version.networkResponseAdapter}"
        const val composeDestinationsCore =
            "io.github.raamcosta.compose-destinations:core:${Version.composeDestinations}"
        const val composeDestinationsKsp =
            "io.github.raamcosta.compose-destinations:ksp:${Version.composeDestinations}"
    }

    object SquareUp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
        const val androidxTestExtJunit = "androidx.test.ext:junit:${Version.androidxTestExtJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Version.compose}"
        const val mockk = "io.mockk:mockk:${Version.mockk}"
        const val coroutinesTesting = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutinesTesting}"
    }
}
