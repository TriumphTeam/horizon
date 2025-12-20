plugins {
    `java-gradle-plugin`
    id("org.gradle.kotlin.kotlin-dsl")
    // id("com.gradle.plugin-publish")
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
    jvmToolchain(17)
}
