import dev.triumphteam.root.KotlinOpt
import dev.triumphteam.root.PreviewFeature

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("dev.triumphteam.root")
}

val optIns = setOf(KotlinOpt.STD, KotlinOpt.CONTRACT)
val previews = setOf(PreviewFeature.CONTEXT_RECEIVERS)

kotlin {
    explicitApi()

    compilerOptions {
        optIn.addAll(
            buildList {
                // Add preview
                // addAll(previews.mapNotNull(PreviewFeature::compilerArg))

                // Add opt-ins
                val optIns = buildString {
                    append(KotlinOpt.OPT_IN)

                    val joinedOpt = optIns.flatMap(KotlinOpt::optIns).joinToString(",")
                    append(joinedOpt)
                }

                add(optIns)
            },
        )
    }
}
