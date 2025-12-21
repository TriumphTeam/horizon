package dev.triumphteam.horizon.vite.extension

import dev.triumphteam.horizon.html.HtmlDocument
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.html.tag.html
import org.gradle.api.Project

public abstract class ViteExtension(private val project: Project) {

    public var index: HtmlDocument = createHtml {
        html {

        }
    }
}

/**
 * <html lang="en">
 *   <head>
 *     <meta charset="UTF-8" />
 *     <link rel="icon" type="image/svg+xml" href="/vite.svg" />
 *     <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 *     <title>Vite + TS</title>
 *   </head>
 *   <body>
 *     <div id="app"></div>
 *     <script type="module" src="/src/main.ts"></script>
 *   </body>
 * </html>
 */
