package dev.triumphteam.horizon.html.element

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.html.FlowTag
import dev.triumphteam.horizon.html.attributes.TagWithAudioVideoAttributes
import dev.triumphteam.horizon.html.attributes.TagWithAutoFocusAttribute
import dev.triumphteam.horizon.html.attributes.TagWithCrossOriginAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDirnameAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDisabledAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDownloadAttribute
import dev.triumphteam.horizon.html.attributes.TagWithForAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormActionAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHeightAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefLangAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMediaAttribute
import dev.triumphteam.horizon.html.attributes.TagWithNameAttribute
import dev.triumphteam.horizon.html.attributes.TagWithPlaceholderAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReadOnlyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRelAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSrcAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTargetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTypeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithValueAttribute
import dev.triumphteam.horizon.html.attributes.TagWithWidthAttribute

public class SimpleFlowTag(
    tagName: String,
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName, parentComponent, initialAttributes)

public class ATag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "a", parentComponent, initialAttributes), TagWithDownloadAttribute, TagWithHrefAttribute,
     TagWithHrefLangAttribute, TagWithMediaAttribute, TagWithRelAttribute, TagWithTargetAttribute, TagWithTypeAttribute,
    TagWithReferrerPolicyAttribute

public class AudioTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "audio", parentComponent, initialAttributes), TagWithAudioVideoAttributes, TagWithSrcAttribute

public class BlockQuoteTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "blockquote", parentComponent, initialAttributes)

public class ButtonTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "button", parentComponent, initialAttributes), TagWithAutoFocusAttribute,
    TagWithDisabledAttribute, TagWithFormAttribute, TagWithFormActionAttribute, TagWithTypeAttribute,
    TagWithNameAttribute, TagWithValueAttribute {

    // TODO: Some extra attributes.
}

public class CanvasTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "canvas", parentComponent, initialAttributes), TagWithWidthAttribute, TagWithHeightAttribute


public class LabelTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "label", parentComponent, initialAttributes), TagWithForAttribute, TagWithFormAttribute

public class LiTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "li", parentComponent, initialAttributes), TagWithValueAttribute

public class OlTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "ol", parentComponent, initialAttributes), TagWithTypeAttribute

public class ScriptTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "script", parentComponent, initialAttributes), TagWithCrossOriginAttribute,
    TagWithReferrerPolicyAttribute, TagWithSrcAttribute, TagWithTypeAttribute

public class StyleTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "style", parentComponent, initialAttributes), TagWithMediaAttribute, TagWithTypeAttribute

public class TextAreaTag(
    parentComponent: Component,
    initialAttributes: Map<String, String>,
) : FlowTag(tagName = "textarea", parentComponent, initialAttributes), TagWithAutoFocusAttribute,
    TagWithDirnameAttribute, TagWithDisabledAttribute, TagWithFormAttribute, TagWithPlaceholderAttribute,
    TagWithNameAttribute, TagWithReadOnlyAttribute {

    // TODO: Some extra attributes.
}
