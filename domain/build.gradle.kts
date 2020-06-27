plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt").version("1.6.0")
}

apply {
    from("$rootDir/ktlint.gradle.kts")
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files("src/main/kotlin", "src/main/java")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    exclude(".*/resources/.*,.*/build/.*")
}

dependencies {
    implementation(Libs.kotlinStdLib)

    implementation(Libs.coroutinesCore)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)
}