package dev.triumphteam.horizon

import dev.triumphteam.horizon.component.functional.component
import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.TagMarker
import dev.triumphteam.horizon.html.a
import dev.triumphteam.horizon.html.br
import dev.triumphteam.horizon.html.button
import dev.triumphteam.horizon.html.div
import dev.triumphteam.horizon.html.footer
import dev.triumphteam.horizon.html.h1
import dev.triumphteam.horizon.html.h2
import dev.triumphteam.horizon.html.i
import dev.triumphteam.horizon.html.img
import dev.triumphteam.horizon.html.p
import dev.triumphteam.horizon.html.span
import dev.triumphteam.horizon.router.navigate
import dev.triumphteam.horizon.state.mutableStateOf
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

public fun main() {
    app {
        route(path = "docs/:versionProject?/:projectPage?/:page?") { route ->
            val versionProject by route["versionProject"]
            val projectPage by route["projectPage"]
            val page by route["page"]

            p {
                text("Version: $versionProject Project: $projectPage Page: $page")
            }
        }

        route("example/:project/:page") {
            println("triggering a full render")
            component {
                val page by remember(it["page"])

                render {
                    println("rendering")
                    div {
                        text("Page: $page")
                    }
                    br()
                    navigate(to = "../page1") {
                        text("page1")
                    }
                    br()
                    navigate(to = "../page2") {
                        text("page2")
                    }
                    br()
                    navigate(to = "../page3") {
                        text("page3")
                    }
                    br()
                    navigate(to = "../page4") {
                        text("page4")
                    }
                }
            }
        }


        index {
            component {
                var open by remember(false)

                render {
                    div {
                        onClick = { open = !open }

                        if (open) div { text("X") } else div { text("-") }
                    }
                }
            }

            div(className = "test") {

                text("Rendering example route 2")

                onClick = { event ->
                    window.alert("Kotlin!")
                }
            }

            component {
                var open by remember(false)

                render {
                    div {
                        onClick = { open = !open }

                        if (open) div { text("X") } else div { text("-") }
                    }
                }
            }

            component {

                var clicks by remember(0)

                render {
                    println("hmm")
                    div { text("1") }

                    component {
                        println("Creating the culprit component!")
                        render { div { text("2") } }
                    }

                    div { text("3") }
                    div(className = "change-$clicks") {
                        text("This should remain unchanged!")
                    }

                    component {
                        println("Creating the second culprit component!")
                        render { div { text("5") } }
                    }

                    div { text("6") }

                    button {
                        onClick = { clicks++ }
                        text("Click me")
                    }
                }
            }

            navigate(to = "/example") {
                text("Example!")
            }

            br()

            navigate(to = "/with-variable/example") {
                text("Example with variable!")
            }
        }

        route("with-variable/:variable") { route ->
            div(id = "parent-div") {
                text("parent div")
            }
        }
    }
}

private data class Project(
    val logo: String,
    val name: String,
    val description: String,
    val versions: List<String>,
)
