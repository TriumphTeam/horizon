plugins {
    id("horizon.base")
}

root {
    configureMultiplatform {
        js {
            target {
                binaries.library()
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
