plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.koti.cake.core.network"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    api(project(":core:domain"))
    api(libs.retrofit)
    api(libs.okhttp)
    api(libs.kotlinx.serialization.json)

    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp.logging.interceptor)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
