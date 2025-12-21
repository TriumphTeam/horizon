package dev.triumphteam.horizon.html.tag.attributes

import dev.triumphteam.horizon.html.tag.ATag
import dev.triumphteam.horizon.html.tag.AreaTag
import dev.triumphteam.horizon.html.tag.BlockQuoteTag
import dev.triumphteam.horizon.html.tag.HtmlTag
import dev.triumphteam.horizon.html.tag.MetaTag
import dev.triumphteam.horizon.html.tag.OlTag
import dev.triumphteam.horizon.html.tag.ScriptTag

public inline var HtmlTag.className: String?
    get() = attributes[HtmlAttributes.CLASS]
    set(value) = setAttribute(HtmlAttributes.CLASS, value)

public inline var HtmlTag.accessKey: String?
    get() = attributes[HtmlAttributes.ACCESS_KEY]
    set(value) = setAttribute(HtmlAttributes.ACCESS_KEY, value)

public inline var HtmlTag.contentEditable: String?
    get() = attributes[HtmlAttributes.CONTENTEDITABLE]
    set(value) = setAttribute(HtmlAttributes.CONTENTEDITABLE, value)

public inline var HtmlTag.dir: String?
    get() = attributes[HtmlAttributes.DIR]
    set(value) = setAttribute(HtmlAttributes.DIR, value)

public inline var HtmlTag.draggable: Boolean?
    get() = attributes[HtmlAttributes.DRAGGABLE]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.DRAGGABLE, value)

public inline var HtmlTag.enterKeyHint: String?
    get() = attributes[HtmlAttributes.ENTER_KEY_HINT]
    set(value) = setAttribute(HtmlAttributes.ENTER_KEY_HINT, value)

public inline var HtmlTag.hidden: Boolean?
    get() = attributes[HtmlAttributes.HIDDEN]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.HIDDEN, value)

public inline var HtmlTag.id: String?
    get() = attributes[HtmlAttributes.ID]
    set(value) = setAttribute(HtmlAttributes.ID, value)

public inline var HtmlTag.inert: String?
    get() = attributes[HtmlAttributes.INERT]
    set(value) = setAttribute(HtmlAttributes.INERT, value)

public inline var HtmlTag.inputMode: String?
    get() = attributes[HtmlAttributes.INPUT_MODE]
    set(value) = setAttribute(HtmlAttributes.INPUT_MODE, value)

public inline var HtmlTag.lang: String?
    get() = attributes[HtmlAttributes.LANG]
    set(value) = setAttribute(HtmlAttributes.LANG, value)

public inline var HtmlTag.popover: String?
    get() = attributes[HtmlAttributes.POPOVER]
    set(value) = setAttribute(HtmlAttributes.POPOVER, value)

public inline var HtmlTag.spellcheck: String?
    get() = attributes[HtmlAttributes.SPELLCHECK]
    set(value) = setAttribute(HtmlAttributes.SPELLCHECK, value)

public inline var HtmlTag.style: String?
    get() = attributes[HtmlAttributes.STYLE]
    set(value) = setAttribute(HtmlAttributes.STYLE, value)

public inline var HtmlTag.tabindex: String?
    get() = attributes[HtmlAttributes.TAB_INDEX]
    set(value) = setAttribute(HtmlAttributes.TAB_INDEX, value)

public inline var HtmlTag.title: String?
    get() = attributes[HtmlAttributes.TITLE]
    set(value) = setAttribute(HtmlAttributes.TITLE, value)

public inline var HtmlTag.translate: String?
    get() = attributes[HtmlAttributes.TRANSLATE]
    set(value) = setAttribute(HtmlAttributes.TRANSLATE, value)

public interface HtmlTagWithNameAttribute : HtmlTag

public inline var HtmlTagWithNameAttribute.name: String?
    get() = attributes[HtmlAttributes.NAME]
    set(value) = setAttribute(HtmlAttributes.NAME, value)

public interface HtmlTagWithTypeAttribute : HtmlTag

public inline var HtmlTagWithTypeAttribute.type: String?
    get() = attributes[HtmlAttributes.TYPE]
    set(value) = setAttribute(HtmlAttributes.TYPE, value)

public interface HtmlTagWithSrcAttribute : HtmlTag

public inline var HtmlTagWithSrcAttribute.src: String?
    get() = attributes[HtmlAttributes.SRC]
    set(value) = setAttribute(HtmlAttributes.SRC, value)

public interface HtmlTagWithFormAttribute : HtmlTag

public inline var HtmlTagWithFormAttribute.form: String?
    get() = attributes[HtmlAttributes.FORM]
    set(value) = setAttribute(HtmlAttributes.FORM, value)

public interface HtmlTagWithDisabledAttribute : HtmlTag

public inline var HtmlTagWithDisabledAttribute.disabled: Boolean?
    get() = attributes[HtmlAttributes.DISABLED]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.DISABLED, value)

public interface HtmlTagWithHeightAttribute : HtmlTag

public inline var HtmlTagWithHeightAttribute.height: Int?
    get() = attributes[HtmlAttributes.HEIGHT]?.toIntOrNull()
    set(value) = setAttribute(HtmlAttributes.HEIGHT, value?.toString())

public inline var HtmlTagWithHeightAttribute.heightRaw: String?
    get() = attributes[HtmlAttributes.HEIGHT]
    set(value) = setAttribute(HtmlAttributes.HEIGHT, value)

public interface HtmlTagWithWidthAttribute : HtmlTag

public inline var HtmlTagWithWidthAttribute.width: Int?
    get() = attributes[HtmlAttributes.WIDTH]?.toIntOrNull()
    set(value) = setAttribute(HtmlAttributes.WIDTH, value?.toString())

public inline var HtmlTagWithWidthAttribute.widthRaw: String?
    get() = attributes[HtmlAttributes.WIDTH]
    set(value) = setAttribute(HtmlAttributes.WIDTH, value)

public interface HtmlTagWithValueAttribute : HtmlTag

public inline var HtmlTagWithValueAttribute.value: String?
    get() = attributes[HtmlAttributes.VALUE]
    set(value) = setAttribute(HtmlAttributes.VALUE, value)

public interface HtmlTagWithMediaAttribute : HtmlTag

public inline var HtmlTagWithMediaAttribute.media: String?
    get() = attributes[HtmlAttributes.MEDIA]
    set(value) = setAttribute(HtmlAttributes.MEDIA, value)

public interface HtmlTagWithHrefAttribute : HtmlTag

public inline var HtmlTagWithHrefAttribute.href: String?
    get() = attributes[HtmlAttributes.HREF]
    set(value) = setAttribute(HtmlAttributes.HREF, value)

public interface HtmlTagWithRelAttribute : HtmlTag

public inline var HtmlTagWithRelAttribute.rel: String?
    get() = attributes[HtmlAttributes.REL]
    set(value) = setAttribute(HtmlAttributes.REL, value)

public interface HtmlTagWithTargetAttribute : HtmlTag

public inline var HtmlTagWithTargetAttribute.target: Target?
    get() = attributes[HtmlAttributes.TARGET]?.let { Target.valueOf(it) }
    set(value) = setAttribute(HtmlAttributes.TARGET, value?.value)

public inline var HtmlTagWithTargetAttribute.targetRaw: String?
    get() = attributes[HtmlAttributes.TARGET]
    set(value) = setAttribute(HtmlAttributes.TARGET, value)

public interface HtmlTagWithAutoFocusAttribute : HtmlTag

public inline var HtmlTagWithAutoFocusAttribute.autoFocus: Boolean?
    get() = attributes[HtmlAttributes.AUTOFOCUS]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.AUTOFOCUS, value)

public interface HtmlTagWithRequiredAttribute : HtmlTag

public inline var HtmlTagWithRequiredAttribute.required: Boolean?
    get() = attributes[HtmlAttributes.REQUIRED]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.REQUIRED, value)

public interface HtmlTagWithMaxAttribute : HtmlTag

public inline var HtmlTagWithMaxAttribute.max: String?
    get() = attributes[HtmlAttributes.MAX]
    set(value) = setAttribute(HtmlAttributes.MAX, value)

public interface HtmlTagWithLabelAttribute : HtmlTag

public inline var HtmlTagWithLabelAttribute.label: String?
    get() = attributes[HtmlAttributes.LABEL]
    set(value) = setAttribute(HtmlAttributes.LABEL, value)

public interface HtmlTagWithAltAttribute : HtmlTag

public inline var HtmlTagWithAltAttribute.alt: String?
    get() = attributes[HtmlAttributes.ALT]
    set(value) = setAttribute(HtmlAttributes.ALT, value)

public interface HtmlTagWithCiteAttribute : HtmlTag

public inline var HtmlTagWithCiteAttribute.cite: String?
    get() = attributes[HtmlAttributes.CITE]
    set(value) = setAttribute(HtmlAttributes.CITE, value)

public interface HtmlTagWithDateTimeAttribute : HtmlTag

public inline var HtmlTagWithDateTimeAttribute.dateTime: String?
    get() = attributes[HtmlAttributes.DATETIME]
    set(value) = setAttribute(HtmlAttributes.DATETIME, value)

public interface HtmlTagWithAudioVideoAttributes : HtmlTag

public inline var HtmlTagWithAudioVideoAttributes.autoplay: Boolean?
    get() = attributes[HtmlAttributes.AUTOPLAY]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.AUTOPLAY, value)

public inline var HtmlTagWithAudioVideoAttributes.controls: Boolean?
    get() = attributes[HtmlAttributes.CONTROLS]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.CONTROLS, value)

public inline var HtmlTagWithAudioVideoAttributes.loop: Boolean?
    get() = attributes[HtmlAttributes.LOOP]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.LOOP, value)

public inline var HtmlTagWithAudioVideoAttributes.muted: Boolean?
    get() = attributes[HtmlAttributes.MUTED]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.MUTED, value)

public inline var HtmlTagWithAudioVideoAttributes.preload: Preload?
    get() = attributes[HtmlAttributes.PRELOAD]?.let { Preload.valueOf(it) }
    set(value) = setAttribute(HtmlAttributes.PRELOAD, value?.value)

public interface HtmlTagWithDirnameAttribute : HtmlTag

public inline var HtmlTagWithDirnameAttribute.dirname: String?
    get() = attributes[HtmlAttributes.DIRNAME]
    set(value) = setAttribute(HtmlAttributes.DIRNAME, value)

public interface HtmlTagWithMaxLengthAttribute : HtmlTag

public inline var HtmlTagWithMaxLengthAttribute.maxLength: String?
    get() = attributes[HtmlAttributes.MAX_LENGTH]
    set(value) = setAttribute(HtmlAttributes.MAX_LENGTH, value)

public interface HtmlTagWithPlaceholderAttribute : HtmlTag

public inline var HtmlTagWithPlaceholderAttribute.placeholder: String?
    get() = attributes[HtmlAttributes.PLACEHOLDER]
    set(value) = setAttribute(HtmlAttributes.PLACEHOLDER, value)

public interface HtmlTagWithReadOnlyAttribute : HtmlTag

public inline var HtmlTagWithReadOnlyAttribute.readOnly: Boolean?
    get() = attributes[HtmlAttributes.READONLY]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.READONLY, value)

public interface HtmlTagWithDownloadAttribute : HtmlTag

public inline var HtmlTagWithDownloadAttribute.download: String?
    get() = attributes[HtmlAttributes.DOWNLOAD]
    set(value) = setAttribute(HtmlAttributes.DOWNLOAD, value)

public interface HtmlTagWithHrefLangAttribute : HtmlTag

public inline var HtmlTagWithHrefLangAttribute.hrefLang: String?
    get() = attributes[HtmlAttributes.HREF_LANG]
    set(value) = setAttribute(HtmlAttributes.HREF_LANG, value)

public interface HtmlTagWithColspanAttribute : HtmlTag

public inline var HtmlTagWithColspanAttribute.colspan: String?
    get() = attributes[HtmlAttributes.COLSPAN]
    set(value) = setAttribute(HtmlAttributes.COLSPAN, value)

public interface HtmlTagWithHeadersAttribute : HtmlTag

public inline var HtmlTagWithHeadersAttribute.headers: String?
    get() = attributes[HtmlAttributes.HEADERS]
    set(value) = setAttribute(HtmlAttributes.HEADERS, value)

public interface HtmlTagWithRowspanAttribute : HtmlTag

public inline var HtmlTagWithRowspanAttribute.rowspan: String?
    get() = attributes[HtmlAttributes.ROWSPAN]
    set(value) = setAttribute(HtmlAttributes.ROWSPAN, value)

public interface HtmlTagWithCharsetAttribute : HtmlTag

public inline var HtmlTagWithCharsetAttribute.charset: String?
    get() = attributes[HtmlAttributes.CHARSET]
    set(value) = setAttribute(HtmlAttributes.CHARSET, value)

public interface HtmlTagWithAutocompleteAttribute : HtmlTag

public inline var HtmlTagWithAutocompleteAttribute.autocomplete: Boolean?
    get() = attributes[HtmlAttributes.AUTOCOMPLETE]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.AUTOCOMPLETE, value)

public interface HtmlTagWithFormActionAttribute : HtmlTag

public inline var HtmlTagWithFormActionAttribute.formAction: String?
    get() = attributes[HtmlAttributes.FORM_ACTION]
    set(value) = setAttribute(HtmlAttributes.FORM_ACTION, value)

public interface HtmlTagWithPopoverTargetAttribute : HtmlTag

public inline var HtmlTagWithPopoverTargetAttribute.popoverTarget: String?
    get() = attributes[HtmlAttributes.POPOVER_TARGET]
    set(value) = setAttribute(HtmlAttributes.POPOVER_TARGET, value)

public interface HtmlTagWithPopoverTargetActionAttribute : HtmlTag

public inline var HtmlTagWithPopoverTargetActionAttribute.popoverTargetAction: String?
    get() = attributes[HtmlAttributes.POPOVER_TARGET_ACTION]
    set(value) = setAttribute(HtmlAttributes.POPOVER_TARGET_ACTION, value)

public interface HtmlTagWithMinAttribute : HtmlTag

public inline var HtmlTagWithMinAttribute.min: String?
    get() = attributes[HtmlAttributes.MIN]
    set(value) = setAttribute(HtmlAttributes.MIN, value)

public interface HtmlTagWithMultipleAttribute : HtmlTag

public inline var HtmlTagWithMultipleAttribute.multiple: String?
    get() = attributes[HtmlAttributes.MULTIPLE]
    set(value) = setAttribute(HtmlAttributes.MULTIPLE, value)

public interface HtmlTagWithSizeAttribute : HtmlTag

public inline var HtmlTagWithSizeAttribute.size: String?
    get() = attributes[HtmlAttributes.SIZE]
    set(value) = setAttribute(HtmlAttributes.SIZE, value)

public interface HtmlTagWithSizesAttribute : HtmlTag

public inline var HtmlTagWithSizesAttribute.sizes: String?
    get() = attributes[HtmlAttributes.SIZES]
    set(value) = setAttribute(HtmlAttributes.SIZES, value)

public interface HtmlTagWithUseMapAttribute : HtmlTag

public inline var HtmlTagWithUseMapAttribute.useMap: String?
    get() = attributes[HtmlAttributes.USE_MAP]
    set(value) = setAttribute(HtmlAttributes.USE_MAP, value)

public interface HtmlTagWithSrcsetAttribute : HtmlTag

public inline var HtmlTagWithSrcsetAttribute.srcset: String?
    get() = attributes[HtmlAttributes.SRCSET]
    set(value) = setAttribute(HtmlAttributes.SRCSET, value)

public interface HtmlTagWithForAttribute : HtmlTag

public inline var HtmlTagWithForAttribute.forId: String?
    get() = attributes[HtmlAttributes.FOR]
    set(value) = setAttribute(HtmlAttributes.FOR, value)

public interface HtmlTagWithSpanAttribute : HtmlTag

public inline var HtmlTagWithSpanAttribute.span: String?
    get() = attributes[HtmlAttributes.SPAN]
    set(value) = setAttribute(HtmlAttributes.SPAN, value)

public interface HtmlTagWithReferrerPolicyAttribute : HtmlTag

public inline var HtmlTagWithReferrerPolicyAttribute.referrerPolicy: ReferrerPolicy?
    get() = attributes[HtmlAttributes.REFERRER_POLICY]?.let { ReferrerPolicy.valueOf(it) }
    set(value) = setAttribute(HtmlAttributes.REFERRER_POLICY, value?.value)

public interface HtmlTagWithCrossOriginAttribute : HtmlTag

public inline var HtmlTagWithCrossOriginAttribute.crossOrigin: CrossOrigin?
    get() = attributes[HtmlAttributes.CROSS_ORIGIN]?.let { CrossOrigin.valueOf(it) }
    set(value) = setAttribute(HtmlAttributes.CROSS_ORIGIN, value?.value)

public inline var ATag.ping: String?
    get() = attributes[HtmlAttributes.PING]
    set(value) = setAttribute(HtmlAttributes.PING, value)

public inline var AreaTag.coords: String?
    get() = attributes[HtmlAttributes.COORDS]
    set(value) = setAttribute(HtmlAttributes.COORDS, value)

public inline var AreaTag.shape: AreaShape?
    get() = attributes[HtmlAttributes.COORDS]?.let { AreaShape.valueOf(it) }
    set(value) = setAttribute(HtmlAttributes.COORDS, value?.value)

public inline var AreaTag.shapeRaw: String?
    get() = attributes[HtmlAttributes.COORDS]
    set(value) = setAttribute(HtmlAttributes.COORDS, value)

public inline var BlockQuoteTag.cite: String?
    get() = attributes[HtmlAttributes.CITE]
    set(value) = setAttribute(HtmlAttributes.CITE, value)

public inline var MetaTag.content: String?
    get() = attributes[HtmlAttributes.CONTENT]
    set(value) = setAttribute(HtmlAttributes.CONTENT, value)

public inline var MetaTag.httpEquiv: String?
    get() = attributes[HtmlAttributes.HTTP_EQUIV]
    set(value) = setAttribute(HtmlAttributes.HTTP_EQUIV, value)

public inline var OlTag.reversed: String?
    get() = attributes[HtmlAttributes.REVERSED]
    set(value) = setAttribute(HtmlAttributes.REVERSED, value)

public inline var OlTag.start: Int?
    get() = attributes[HtmlAttributes.START]?.toIntOrNull()
    set(value) = setAttribute(HtmlAttributes.START, value?.toString())

public inline var ScriptTag.async: Boolean?
    get() = attributes[HtmlAttributes.ASYNC]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.ASYNC, value)

public inline var ScriptTag.defer: Boolean?
    get() = attributes[HtmlAttributes.DEFER]?.toBoolean()
    set(value) = setAttribute(HtmlAttributes.DEFER, value)

public inline var ScriptTag.integrity: String?
    get() = attributes[HtmlAttributes.INTEGRITY]
    set(value) = setAttribute(HtmlAttributes.INTEGRITY, value)

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
