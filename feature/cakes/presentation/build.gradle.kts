plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.koti.cake.feature.cakes.presentation"
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

}

dependencies {
    implementation(project(":feature:cakes:domain"))
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
