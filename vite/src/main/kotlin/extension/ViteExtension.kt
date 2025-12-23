package dev.triumphteam.horizon.vite.extension

import dev.triumphteam.horizon.html.HtmlDocument
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.html.tag.body
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.html.tag.head
import dev.triumphteam.horizon.html.tag.html
import dev.triumphteam.horizon.html.tag.meta
import dev.triumphteam.horizon.html.tag.title
import org.gradle.api.Project

public abstract class ViteExtension(private val project: Project) {

    public var index: HtmlDocument = createHtml {
        html {
            lang = "en"

            head {
                meta { charset = "UTF-8" }
                title { text("Vite + Horizon") }
            }

            body {
                div { id = "app" }
            }
        }
    }
}
