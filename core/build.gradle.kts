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
            }
        }
    }
}
