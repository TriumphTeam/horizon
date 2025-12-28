package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.div

public fun main() {

    val mine = createTestHtml {
        div {
            id = "parent-div"
            component()
        }
    }

    println(HtmlStringRenderer(true, mine).render())
    // println("Equal: ${mine == hmm}")
}
