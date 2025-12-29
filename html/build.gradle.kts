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
        }
    }
}
