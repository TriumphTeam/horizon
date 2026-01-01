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
        }

        jvm {
            target {
                jvmVersion(17)
            }
        }
    }
}
