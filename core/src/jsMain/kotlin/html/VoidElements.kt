package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.html.attributes.TagWithAltAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDownloadAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefLangAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMediaAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRelAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTargetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTypeAttribute

public class VoidTag(
    tagName: String,
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName, parentComponent, initialAttributes)

public class AreaTag(
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName = "area", parentComponent, initialAttributes), TagWithAltAttribute, TagWithDownloadAttribute,
    TagWithHrefAttribute, TagWithHrefLangAttribute, TagWithMediaAttribute, TagWithRelAttribute, TagWithTargetAttribute,
    TagWithTypeAttribute, TagWithReferrerPolicyAttribute {

    // TODO: Implement missing attributes.
}


