package dev.triumphteam.horizon.html.element

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.html.AbstractTag
import dev.triumphteam.horizon.html.attributes.TagWithAltAttribute
import dev.triumphteam.horizon.html.attributes.TagWithAutoFocusAttribute
import dev.triumphteam.horizon.html.attributes.TagWithAutocompleteAttribute
import dev.triumphteam.horizon.html.attributes.TagWithCharsetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithCrossOriginAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDisabledAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDownloadAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormActionAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHeightAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefLangAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMaxAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMediaAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMinAttribute
import dev.triumphteam.horizon.html.attributes.TagWithNameAttribute
import dev.triumphteam.horizon.html.attributes.TagWithPlaceholderAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReadOnlyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRelAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRequiredAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSizeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSizesAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSrcAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTargetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTypeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithValueAttribute
import dev.triumphteam.horizon.html.attributes.TagWithWidthAttribute

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

public class BaseTag(
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName = "base", parentComponent, initialAttributes), TagWithHrefAttribute, TagWithTargetAttribute

public class ImgTag(
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName = "img", parentComponent, initialAttributes), TagWithAltAttribute, TagWithHeightAttribute,
    TagWithWidthAttribute, TagWithReferrerPolicyAttribute, TagWithSizesAttribute, TagWithSrcAttribute {

    // TODO: Some extra attributes.
}

public class InputTag(
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName = "input", parentComponent, initialAttributes), TagWithAltAttribute,
    TagWithAutocompleteAttribute, TagWithAutoFocusAttribute, TagWithDisabledAttribute, TagWithFormAttribute,
    TagWithFormActionAttribute, TagWithHeightAttribute, TagWithWidthAttribute, TagWithMaxAttribute, TagWithMinAttribute,
    TagWithPlaceholderAttribute, TagWithNameAttribute, TagWithReadOnlyAttribute, TagWithRequiredAttribute,
    TagWithSizeAttribute, TagWithSrcAttribute, TagWithTypeAttribute, TagWithValueAttribute {

    // TODO: Some extra attributes.
}

public class LinkTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : AbstractTag(tagName = "link", parentComponent, initialAttributes), TagWithHrefAttribute, TagWithHrefLangAttribute,
    TagWithReferrerPolicyAttribute, TagWithRelAttribute, TagWithSizesAttribute, TagWithMediaAttribute,
    TagWithTypeAttribute, TagWithCrossOriginAttribute {

    // TODO: Some extra attributes.
}

public class MetaTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : AbstractTag(tagName = "meta", parentComponent, initialAttributes), TagWithCharsetAttribute, TagWithNameAttribute

