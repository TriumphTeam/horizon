package dev.triumphteam.horizon.html.visitor

import dev.triumphteam.horizon.html.tag.TagRenderer

public interface TagVisitor {
    public val renderer: TagRenderer
}
