package dev.triumphteam.horizon.html.tag.attributes

import dev.triumphteam.horizon.html.HtmlTag

public interface HtmlTagWithNameAttribute : HtmlTag
public interface HtmlTagWithTypeAttribute : HtmlTag
public interface HtmlTagWithSrcAttribute : HtmlTag
public interface HtmlTagWithFormAttribute : HtmlTag
public interface HtmlTagWithDisabledAttribute : HtmlTag
public interface HtmlTagWithHeightAttribute : HtmlTag
public interface HtmlTagWithWidthAttribute : HtmlTag
public interface HtmlTagWithValueAttribute : HtmlTag
public interface HtmlTagWithMediaAttribute : HtmlTag
public interface HtmlTagWithHrefAttribute : HtmlTag
public interface HtmlTagWithRelAttribute : HtmlTag
public interface HtmlTagWithTargetAttribute : HtmlTag
public interface HtmlTagWithAutoFocusAttribute : HtmlTag
public interface HtmlTagWithRequiredAttribute : HtmlTag
public interface HtmlTagWithMaxAttribute : HtmlTag
public interface HtmlTagWithLabelAttribute : HtmlTag
public interface HtmlTagWithAltAttribute : HtmlTag
public interface HtmlTagWithCiteAttribute : HtmlTag
public interface HtmlTagWithDateTimeAttribute : HtmlTag
public interface HtmlTagWithAudioVideoAttributes : HtmlTag
public interface HtmlTagWithDirnameAttribute : HtmlTag
public interface HtmlTagWithMaxLengthAttribute : HtmlTag
public interface HtmlTagWithPlaceholderAttribute : HtmlTag
public interface HtmlTagWithReadOnlyAttribute : HtmlTag
public interface HtmlTagWithDownloadAttribute : HtmlTag
public interface HtmlTagWithHrefLangAttribute : HtmlTag
public interface HtmlTagWithColspanAttribute : HtmlTag
public interface HtmlTagWithHeadersAttribute : HtmlTag
public interface HtmlTagWithRowspanAttribute : HtmlTag
public interface HtmlTagWithCharsetAttribute : HtmlTag
public interface HtmlTagWithAutocompleteAttribute : HtmlTag
public interface HtmlTagWithFormActionAttribute : HtmlTag
public interface HtmlTagWithPopoverTargetAttribute : HtmlTag
public interface HtmlTagWithPopoverTargetActionAttribute : HtmlTag
public interface HtmlTagWithMinAttribute : HtmlTag
public interface HtmlTagWithMultipleAttribute : HtmlTag
public interface HtmlTagWithSizeAttribute : HtmlTag
public interface HtmlTagWithSizesAttribute : HtmlTag
public interface HtmlTagWithUseMapAttribute : HtmlTag
public interface HtmlTagWithSrcsetAttribute : HtmlTag
public interface HtmlTagWithForAttribute : HtmlTag
public interface HtmlTagWithSpanAttribute : HtmlTag
public interface HtmlTagWithReferrerPolicyAttribute : HtmlTag
public interface HtmlTagWithCrossOriginAttribute : HtmlTag

@PublishedApi
internal fun HtmlTag.setAttribute(attribute: String, value: String?) {
    if (value == null) attributes.remove(attribute) else attributes[attribute] = value
    renderer.onAttribute(this, attribute, value)
}

@PublishedApi
internal fun HtmlTag.setAttribute(attribute: String, value: Boolean?) {
    if (value == null) attributes.remove(attribute) else attributes[attribute] = value.toString()
    renderer.onAttribute(this, attribute, "")
}
