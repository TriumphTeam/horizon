package dev.triumphteam.horizon.html.attributes

import dev.triumphteam.horizon.html.Tag

public interface TagWithNameAttribute : Tag
public interface TagWithTypeAttribute : Tag
public interface TagWithSrcAttribute : Tag
public interface TagWithFormAttribute : Tag
public interface TagWithDisabledAttribute : Tag
public interface TagWithHeightAttribute : Tag
public interface TagWithWidthAttribute : Tag
public interface TagWithValueAttribute : Tag
public interface TagWithMediaAttribute : Tag
public interface TagWithHrefAttribute : Tag
public interface TagWithRelAttribute : Tag
public interface TagWithTargetAttribute : Tag
public interface TagWithAutoFocusAttribute : Tag
public interface TagWithRequiredAttribute : Tag
public interface TagWithMaxAttribute : Tag
public interface TagWithLabelAttribute : Tag
public interface TagWithAltAttribute : Tag
public interface TagWithCiteAttribute : Tag
public interface TagWithDateTimeAttribute : Tag
public interface TagWithAudioVideoAttributes : Tag
public interface TagWithDirnameAttribute : Tag
public interface TagWithMaxLengthAttribute : Tag
public interface TagWithPlaceholderAttribute : Tag
public interface TagWithReadOnlyAttribute : Tag
public interface TagWithDownloadAttribute : Tag
public interface TagWithHrefLangAttribute : Tag
public interface TagWithColspanAttribute : Tag
public interface TagWithHeadersAttribute : Tag
public interface TagWithRowspanAttribute : Tag
public interface TagWithCharsetAttribute : Tag
public interface TagWithAutocompleteAttribute : Tag
public interface TagWithFormActionAttribute : Tag
public interface TagWithPopoverTargetAttribute : Tag
public interface TagWithPopoverTargetActionAttribute : Tag
public interface TagWithMinAttribute : Tag
public interface TagWithMultipleAttribute : Tag
public interface TagWithSizeAttribute : Tag
public interface TagWithSizesAttribute : Tag
public interface TagWithUseMapAttribute : Tag
public interface TagWithSrcsetAttribute : Tag
public interface TagWithForAttribute : Tag
public interface TagWithSpanAttribute : Tag
public interface TagWithReferrerPolicyAttribute : Tag
public interface TagWithCrossOriginAttribute : Tag

@PublishedApi
internal fun Tag.setAttribute(attribute: String, value: String?) {
    if (value == null) {
        element.removeAttribute(attribute)
        return
    }

    if (attribute == "checked" || attribute == "selected" || attribute == "disabled" || attribute == "popover") {
        element.asDynamic()[attribute] = value.toBoolean()
        return
    }

    element.setAttribute(attribute, value)
}

@PublishedApi
internal fun Tag.setAttribute(attribute: String, value: Boolean?) {
    setAttribute(attribute, value?.toString())
}

@PublishedApi
internal fun Tag.getAttribute(attribute: String): String? = element.getAttribute(attribute)
