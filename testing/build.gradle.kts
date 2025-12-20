plugins {
    id("horizon.base")
    // id("dev.opensavvy.vite.kotlin") version "0.7.1"
}

kotlin {
    kotlin {
        js(IR) {
            binaries.executable()
            nodejs()
            /*browser()
            binaries.executable()*/
        }
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(projects.horizonCore)
            }
        }
    }
}
