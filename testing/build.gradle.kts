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
                implementation(projects.horizonCore)
            }
        }
    }
}
