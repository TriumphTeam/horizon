plugins {
    id("horizon.base")
}

kotlin {
    kotlin {
        js(IR) {
            browser()
            binaries.executable()
        }
    }

    sourceSets {
        jsMain {
            dependencies {
                api(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-html-js:0.12.0")
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
            }
        }
    }
}
