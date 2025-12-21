package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.attributes.alt
import dev.triumphteam.horizon.html.tag.attributes.className
import dev.triumphteam.horizon.html.tag.attributes.height
import dev.triumphteam.horizon.html.tag.attributes.href
import dev.triumphteam.horizon.html.tag.attributes.lang
import dev.triumphteam.horizon.html.tag.attributes.placeholder
import dev.triumphteam.horizon.html.tag.attributes.rel
import dev.triumphteam.horizon.html.tag.attributes.required
import dev.triumphteam.horizon.html.tag.attributes.src
import dev.triumphteam.horizon.html.tag.attributes.width
import dev.triumphteam.horizon.html.tag.body
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.html.tag.head
import dev.triumphteam.horizon.html.tag.html
import dev.triumphteam.horizon.html.tag.img
import dev.triumphteam.horizon.html.tag.input
import dev.triumphteam.horizon.html.tag.link
import dev.triumphteam.horizon.html.tag.title
import kotlinx.html.body
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.img
import kotlinx.html.input
import kotlinx.html.lang
import kotlinx.html.link
import kotlinx.html.stream.createHTML
import kotlinx.html.title

public fun main() {

    val mine = HtmlStringRenderer(true)
        .apply {
            html {
                lang = "en"

                head {
                    link {
                        rel = "stylesheet"
                        href = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                    }
                    title {
                        text("My title")
                    }
                }
                body {
                    div {
                        className = "example"

                        text("before div")

                        div(className = "another") {
                            img {
                                src = "https://via.placeholder.com/150"
                                alt = "Placeholder"
                                width = 150
                                height = 150
                            }

                            input {
                                required = true
                                placeholder = "Placeholder"
                            }

                            text("another")
                        }

                        text("after div")
                    }
                }
            }
        }.render()

    println("<!DOCTYPE html>\n$mine")

    println("--------------------")

    val hmm = createHTML().html {
        lang = "en"

        head {
            link {
                rel = "stylesheet"
                href = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            }
            title {
                text("My title")
            }
        }
        body {
            div {
                classes = setOf("example")

                text("before div")

                div(classes = "another") {
                    img {
                        src = "https://via.placeholder.com/150"
                        alt = "Placeholder"
                        width = 150.toString()
                        height = 150.toString()
                    }

                    input {
                        required = true
                        placeholder = "Placeholder"
                    }

                    text("another")
                }

                text("after div")
            }
        }
    }

    println("<!DOCTYPE html>\n$hmm")

    // println("Equal: ${mine == hmm}")
}
