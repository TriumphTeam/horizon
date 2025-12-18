plugins {
    id("horizon.vite")
    // id("dev.opensavvy.vite.kotlin") version "0.7.1"
}

kotlin {
    kotlin {
        js(IR) {
            /*browser {
                commonWebpackConfig {
                    devServer = KotlinWebpackConfig.DevServer(
                        open = false,
                        port = 3000,
                        static = mutableListOf("${layout.buildDirectory.asFile.get()}/processedResources/js/main")
                    )
                }
            }
            binaries.executable()
            nodejs()*/
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
