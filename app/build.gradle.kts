plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs")
    id("io.gitlab.arturbosch.detekt").version("1.1.1")
}

apply {
    from("$rootDir/ktlint.gradle.kts")
}

val apiBaseUrl: String by project
val openweathermapApiKey: String by project

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.akoufatzis.coolweather"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField(
            "String", "OPENWEATHERMAP_URL",
            "\"$apiBaseUrl\""
        )
        buildConfigField(
            "String", "OPENWEATHERMAP_API_KEY",
            "\"$openweathermapApiKey\""
        )

        useLibrary("android.test.runner")
        useLibrary("android.test.base")
        useLibrary("android.test.mock")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    dataBinding.isEnabled = true
    viewBinding.isEnabled = true

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

detekt {
    toolVersion = "1.1.1"
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files("src/main/kotlin", "src/main/java")
    filters = ".*/resources/.*,.*/build/.*"
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.viewpager2)

    implementation(Libs.coreCtx)

    implementation(Libs.viewModelKtx)
    implementation(Libs.lifecycleExt)
    implementation(Libs.lifecycleKtx)
    kapt(Libs.lifecycleCompiler)

    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)

    implementation(Libs.material)
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    implementation(Libs.dagger)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerAndroidSupport)
    kapt(Libs.daggerAndroidProcessor)
    kapt(Libs.daggerCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)

    implementation(Libs.moshi)
    kapt(Libs.moshiCodeGen)

    implementation(Libs.glide)
    kapt(Libs.glideCompiler)

    implementation(Libs.circleIndicator)

    testImplementation(Libs.coroutinesCore)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.coreTesting)
    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
    testImplementation(Libs.coroutinesTest)
    androidTestImplementation(Libs.testRunner)
    androidTestImplementation(Libs.espresso)
}
