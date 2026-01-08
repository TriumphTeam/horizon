plugins {
    id("horizon.base")
    id("horizon.library")
}

root {
    configureMultiplatform {
        js {
            target {
                nodejs()
                binaries.library()
                useEsModules()
            }

            dependencies {
                api(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
            }
        }
    }
}
