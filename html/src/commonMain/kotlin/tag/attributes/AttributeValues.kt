package dev.triumphteam.horizon.html.tag.attributes

public enum class ReferrerPolicy(public val value: String) {
    NO_REFERRER("no-referrer"),
    NO_REFERRER_WHEN_DOWNGRADE("no-referrer-when-downgrade"),
    ORIGIN("origin"),
    ORIGIN_WHEN_CROSS_ORIGIN("origin-when-cross-origin"),
    SAME_ORIGIN("same-origin"),
    STRICT_ORIGIN_WHEN_CROSS_ORIGIN("strict-origin-when-cross-origin"),
    UNSAFE_URL("unsafe-url");
}

public enum class Target(public val value: String) {
    BLANK("_blank"),
    SELF("_self"),
    PARENT("_parent"),
    TOP("_top");
}

public enum class AreaShape(public val value: String) {
    DEFAULT("default"),
    RECT("rect"),
    CIRCLE("circle"),
    POLY("poly");
}

public enum class Preload(public val value: String) {
    NONE("none"),
    METADATA("metadata"),
    AUTO("auto");
}
