plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt").version("1.1.1")
}

apply {
    from("$rootDir/ktlint.gradle.kts")
}

detekt {
    toolVersion = "1.1.1"
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files("src/main/kotlin", "src/main/java")
    filters = ".*/resources/.*,.*/build/.*"
}

dependencies {
    implementation(Libs.kotlinStdLib)

    implementation(Libs.coroutinesCore)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)
}