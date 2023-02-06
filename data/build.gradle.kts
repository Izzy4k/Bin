plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}
repositories {
    google()
    mavenCentral()
}
android {
    namespace = Config.namespaceData
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK

        buildConfigField("String", "BASE_URL", "\"https://lookup.binlist.net/\"")

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
    implementation(Dependencies.Android.coreKtx)

    //Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    //Coroutines
    implementation(Dependencies.Coroutines.coreCoroutines)

    //KTX
    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.lifecycleViewModel)
    implementation(Dependencies.Lifecycle.lifecycleFragment)

    //Room
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.runtime)
    kapt(Dependencies.Room.compiler)

    //Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.convertor_gson)

    //Interceptor
    implementation(Dependencies.Interceptor.interceptor)

    //OkHttp
    implementation(Dependencies.OkHttpClient.okhttp)
    implementation(Dependencies.OkHttpClient.okhttp_bom)
    implementation(Dependencies.OkHttpClient.logging_interceptor)
    implementation(Dependencies.OkHttpClient.legacy_support)


    implementation(project(":core"))
    implementation(project(":domain"))
}