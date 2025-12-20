plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("dev.triumphteam.root")
}

root {
    configureMultiplatform {
        explicitApi()
        optInAll()
        optInAll()
        previewAll()
    }
}
