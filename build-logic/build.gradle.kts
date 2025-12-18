import dev.triumphteam.root.root

plugins {
    `kotlin-dsl`
    id("dev.triumphteam.root.logic") version "0.0.31"
}

dependencies {
    // Hack to allow version catalog inside convention plugins
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    // Bundled plugins
    implementation(libs.bundles.build)

    implementation("dev.opensavvy.gradle.vite:vite-kotlin:0.7.1")

    root("0.0.31")
}
