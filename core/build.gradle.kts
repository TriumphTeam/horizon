plugins {
    id("horizon.base")
}

kotlin {
    kotlin {
        js(IR) {
            nodejs()
            binaries.library()
            useEsModules()
        }
    }

    sourceSets {
        jsMain {
            dependencies {
                api(projects.horizonHtml)
                api(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
            }
        }
    }
}
