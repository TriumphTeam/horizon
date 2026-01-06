plugins {
    id("horizon.base")
    id("horizon.library")
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
                api(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
            }
        }
    }
}
