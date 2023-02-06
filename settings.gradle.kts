pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.1.3")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.7.0")
            }
            if (requested.id.id.startsWith("dagger.hilt.android")) {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.44")
            }
            if (requested.id.id.startsWith("androidx.navigation")) {
                useModule("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
            }
        }
    }
}
rootProject.name = "Bin"
include (":app")
include(":core")
include(":data")
include(":domain")
