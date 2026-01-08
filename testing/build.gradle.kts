plugins {
    id("horizon.base")
}

root {
    configureMultiplatform {
        js {
            target {
                browser()
                binaries.executable()
                useEsModules()
            }

            dependencies {
                implementation(projects.horizonCore)
            }
        }
    }
}
