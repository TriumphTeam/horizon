plugins {
    id("horizon.base")
}

root {
    configureMultiplatform {
        js {
            target {
                nodejs()
                binaries.library()
                useEsModules()
            }
        }

        jvm {
            target {
                jvmVersion(17)
            }

            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")
            }
        }
    }
}
