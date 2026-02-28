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

public fun FlowContent.projects() {
    div(className = "flex flex-wrap items-center justify-center gap-4 mt-12") {
        component {
            /*val example by rememberApiCallState {
                client.get(ApiProjects).body<List<ProjectData>>()
            }*/
            var test by remember(false)

            launch {
                delay(2000L)
                test = true
            }

            render {
                println("re-rendering")
                if (test) {
                    projectBadge(Project("uh", "name", "", listOf()))
                } else {
                    projectBadge(null)
                    // projectBadge(null)
                }
                /*example.fold(
                    onSuccess = {
                        projectBadge(Project("uh", "name", "", listOf()))
                    },
                    onWaiting = {
                        projectBadge(null)
                        projectBadge(null)
                    },
                )*/
            }
        }
    }
}

private fun FlowContent.projectBadge(project: Project?) {
    a(
        className = "group flex items-center gap-3 px-4 py-2.5 bg-dark-surface/60 backdrop-blur-sm rounded-md border border-dark-accent hover:border-primary/50 hover:scale-105 transition-all duration-200 hover:shadow-lg hover:shadow-primary/10",
    ) {
        if (project == null) {
            println("loading cuz null")
            div(className = "w-6 h-6 border-2 border-primary/30 border-t-primary rounded-full animate-spin")
            span(className = "text-white/40 text-sm font-medium") {
                text("Loading...")
            }
        } else {
            println("not loading cuz nit null")
            img(
                className = "w-6 h-6 object-contain",
                src = project.logo,
                alt = "${project.name} logo",
            )
            println("it stops right here")
            // TODO: Something is happening when updating where this doesn't do it
            // This gets skipped, if it's the same as the next both get skipped
            span(className = "text-white/80 group-hover:text-white text-sm font-medium transition-colors duration-200") {
                text("wuaht the fuck")
            }
        }
    }
}

public const val DOTTED_BACKGROUND: String = "bg-[radial-gradient(#202023_1px,transparent_1px)] [background-size:16px_16px]"

@TagMarker
public fun FlowContent.simpleButton(children: FlowContent.() -> Unit) {
    button(className = "flex justify-center cursor-pointer text-2xl text-dark-text bg-dark-accent hover:bg-dark-accent/80 rounded-md p-4 hover:scale-110") {
        children()
    }
}

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

        route("example") {
            div(className = "w-screen min-h-screen $DOTTED_BACKGROUND") {
                div(className = "absolute inset-0 h-full w-full bg-transparent [background:radial-gradient(125%_125%_at_50%_10%,transparent_20%,#141417_70%)]")

                div(className = "relative grid grid-rows-1 w-full min-h-[85vh] justify-items-center content-center px-4 py-12 overflow-hidden") {
                    div(className = "relative z-10 row-span-1 self-center max-w-4xl w-full") {
                        div(className = "flex flex-col items-center gap-6") {
                            div(className = "relative") {
                                div(className = "absolute inset-0 blur-3xl bg-purple-600/40 rounded-full") {}
                                img(
                                    className = "relative h-32 lg:h-40 drop-shadow-2xl transition-transform hover:scale-105 duration-300",
                                    src = "/assets/logo.png",
                                    alt = "Logo",
                                )
                            }

                            h1(className = "text-dark-text text-center text-6xl lg:text-8xl font-bold uppercase leading-tight") {
                                text("Triumph Team")
                            }

                            h2(className = "text-light-text/80 text-center text-lg lg:text-xl font-light tracking-wide max-w-2xl px-4") {
                                text("Making libraries for your block game projects.")
                            }

                            div(className = "flex justify-center gap-4 mt-6") {
                                simpleButton {
                                    i(className = "bxl bx-discord-alt")
                                }
                                simpleButton {
                                    i(className = "bxl bx-github")
                                }
                            }

                            projects()
                        }
                    }
                }
            }

            footer(className = "w-full border-t border-white/10") {
                div(className = "max-w-7xl mx-auto px-6 py-8") {
                    div(className = "flex justify-center items-center") {
                        // Copyright
                        p(className = "text-white/50 text-sm") {
                            text("© ${js("new Date().getFullYear()")} Triumph Team. All rights reserved.")
                        }
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
