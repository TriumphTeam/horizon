import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("horizon.base")
}

kotlin {
    kotlin {
        js(IR) {
            browser {
                commonWebpackConfig {
                    devServer = KotlinWebpackConfig.DevServer(
                        open = false,
                        port = 3000,
                        static = mutableListOf("${layout.buildDirectory.asFile.get()}/processedResources/js/main")
                    )
                }
            }
            binaries.executable()
            nodejs()
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
