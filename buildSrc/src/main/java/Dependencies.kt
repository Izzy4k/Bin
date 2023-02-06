object Dependencies {

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val material = "com.google.android.material:material:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.0"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
    }

    object Hilt {
        private const val version = "2.44"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Lifecycle {
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val lifecycleFragment = "androidx.fragment:fragment-ktx:1.5.5"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    }

    object Test {
        const val jUnit = "junit:junit:4.+"
        const val androidJUnit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
    }

    object NavigationComponent {
        private const val nav_version = "2.5.3"
        const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val nav_ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
    }

    object Coroutines {
        const val coreCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
    }

    object ViewBinding {
        const val binding = "androidx.databinding:viewbinding:7.2.1"
    }

    object Room{
        private const val version = "2.5.0"
        const val ktx = "androidx.room:room-ktx:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }
    object Retrofit {
        private const val retrofit_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val convertor_gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    }

    object Interceptor {
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
    }

    object OkHttpClient {
        const val okhttp_bom = "com.squareup.okhttp3:okhttp-bom:4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp"
        const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor"
        const val legacy_support = "androidx.legacy:legacy-support-v4:1.0.0"
    }
}