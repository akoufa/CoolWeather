plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id( "androidx.navigation.safeargs")
    id("io.gitlab.arturbosch.detekt").version("1.0.0-RC11")
}

apply{
    from("$rootDir/ktlint.gradle.kts")
}

val apiBaseUrl: String by project
val apiKey: String by project

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.akoufatzis.coolweather"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "OPENWEATHERMAP_URL",
            "\"$apiBaseUrl\"")
        buildConfigField("String", "OPENWEATHERMAP_API_KEY",
            "\"$apiKey\"")

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
}

detekt {
    toolVersion = "1.0.0-RC11"
    input = files("src/main/kotlin", "src/main/java")
    filters = ".*/resources/.*,.*/build/.*"
}

dependencies {
    // implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation(Libs.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)

    implementation(Libs.viewModelKtx)
    implementation(Libs.lifecycleExt)
    implementation(Libs.lifecycleRx)
    kapt(Libs.lifecycleCompiler)

    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)

    implementation(Libs.material)

    implementation(Libs.roomRuntime)
    implementation(Libs.roomRx)
    kapt(Libs.roomCompiler)

    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    implementation(Libs.coroutinesRx2)

    implementation(Libs.dagger)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerAndroidSupport)
    kapt(Libs.daggerAndroidProcessor)
    kapt(Libs.daggerCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)
    implementation(Libs.retrofitCouroutines)

    implementation(Libs.moshi)
    kapt(Libs.moshiCodeGen)

    implementation(Libs.glide)
    kapt(Libs.glideCompiler)

    testImplementation(Libs.mockito)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.coreTesting)
    testImplementation(Libs.junit)
    testImplementation(Libs.hamcrest)
    androidTestImplementation(Libs.testRunner)
    androidTestImplementation(Libs.espresso)
}
