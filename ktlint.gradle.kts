repositories {
    jcenter()
}

val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.35.0")
}

task<JavaExec>("ktlint"){
    group = "verification"
    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

task<JavaExec>("ktlintFormat"){
    group = "formatting"
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}