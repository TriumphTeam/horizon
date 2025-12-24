plugins {
    id("horizon.base")
    // id("dev.opensavvy.vite.kotlin") version "0.7.1"
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
                implementation(projects.horizonCore)
                api("org.jetbrains.kotlinx:kotlinx-html-js:0.12.0")
            }
        }
    }
}

// kotlinStoreYarnLock
