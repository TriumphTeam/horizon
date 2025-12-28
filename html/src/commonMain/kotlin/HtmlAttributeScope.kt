package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.ATag
import dev.triumphteam.horizon.html.tag.AreaTag
import dev.triumphteam.horizon.html.tag.BlockQuoteTag
import dev.triumphteam.horizon.html.tag.MetaTag
import dev.triumphteam.horizon.html.tag.OlTag
import dev.triumphteam.horizon.html.tag.ScriptTag
import dev.triumphteam.horizon.html.tag.attributes.AreaShape
import dev.triumphteam.horizon.html.tag.attributes.CrossOrigin
import dev.triumphteam.horizon.html.tag.attributes.HtmlAttributes
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAltAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAudioVideoAttributes
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAutoFocusAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAutocompleteAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithCharsetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithCiteAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithColspanAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithCrossOriginAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDateTimeAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDirnameAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDisabledAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDownloadAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithForAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithFormActionAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithFormAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHeadersAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHeightAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHrefAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHrefLangAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithLabelAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMaxAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMaxLengthAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMediaAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMinAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMultipleAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithNameAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithPlaceholderAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithPopoverTargetActionAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithPopoverTargetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithReadOnlyAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithRelAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithRequiredAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithRowspanAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSizeAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSizesAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSpanAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSrcAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSrcsetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithTargetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithTypeAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithUseMapAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithValueAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithWidthAttribute
import dev.triumphteam.horizon.html.tag.attributes.Preload
import dev.triumphteam.horizon.html.tag.attributes.ReferrerPolicy
import dev.triumphteam.horizon.html.tag.attributes.Target
import dev.triumphteam.horizon.html.tag.attributes.setAttribute

public abstract class HtmlAttributeScope : HtmlTag {

    override val renderer: HtmlRenderer = parentRenderer

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

    public inline var HtmlTagWithNameAttribute.name: String?
        get() = attributes[HtmlAttributes.NAME]
        set(value) = setAttribute(HtmlAttributes.NAME, value)

    public inline var HtmlTagWithTypeAttribute.type: String?
        get() = attributes[HtmlAttributes.TYPE]
        set(value) = setAttribute(HtmlAttributes.TYPE, value)

    public inline var HtmlTagWithSrcAttribute.src: String?
        get() = attributes[HtmlAttributes.SRC]
        set(value) = setAttribute(HtmlAttributes.SRC, value)

    public inline var HtmlTagWithFormAttribute.form: String?
        get() = attributes[HtmlAttributes.FORM]
        set(value) = setAttribute(HtmlAttributes.FORM, value)

    public inline var HtmlTagWithDisabledAttribute.disabled: Boolean?
        get() = attributes[HtmlAttributes.DISABLED]?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.DISABLED, value)

    public inline var HtmlTagWithHeightAttribute.height: Int?
        get() = attributes[HtmlAttributes.HEIGHT]?.toIntOrNull()
        set(value) = setAttribute(HtmlAttributes.HEIGHT, value?.toString())

    public inline var HtmlTagWithHeightAttribute.heightRaw: String?
        get() = attributes[HtmlAttributes.HEIGHT]
        set(value) = setAttribute(HtmlAttributes.HEIGHT, value)

    public inline var HtmlTagWithWidthAttribute.width: Int?
        get() = attributes[HtmlAttributes.WIDTH]?.toIntOrNull()
        set(value) = setAttribute(HtmlAttributes.WIDTH, value?.toString())

    public inline var HtmlTagWithWidthAttribute.widthRaw: String?
        get() = attributes[HtmlAttributes.WIDTH]
        set(value) = setAttribute(HtmlAttributes.WIDTH, value)

    public inline var HtmlTagWithValueAttribute.value: String?
        get() = attributes[HtmlAttributes.VALUE]
        set(value) = setAttribute(HtmlAttributes.VALUE, value)

    public inline var HtmlTagWithMediaAttribute.media: String?
        get() = attributes[HtmlAttributes.MEDIA]
        set(value) = setAttribute(HtmlAttributes.MEDIA, value)

    public inline var HtmlTagWithHrefAttribute.href: String?
        get() = attributes[HtmlAttributes.HREF]
        set(value) = setAttribute(HtmlAttributes.HREF, value)

    public inline var HtmlTagWithRelAttribute.rel: String?
        get() = attributes[HtmlAttributes.REL]
        set(value) = setAttribute(HtmlAttributes.REL, value)

    public inline var HtmlTagWithTargetAttribute.target: Target?
        get() = attributes[HtmlAttributes.TARGET]?.let { Target.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.TARGET, value?.value)

    public inline var HtmlTagWithTargetAttribute.targetRaw: String?
        get() = attributes[HtmlAttributes.TARGET]
        set(value) = setAttribute(HtmlAttributes.TARGET, value)

    public inline var HtmlTagWithAutoFocusAttribute.autoFocus: Boolean?
        get() = attributes[HtmlAttributes.AUTOFOCUS]?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.AUTOFOCUS, value)

    public inline var HtmlTagWithRequiredAttribute.required: Boolean?
        get() = attributes[HtmlAttributes.REQUIRED]?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.REQUIRED, value)

    public inline var HtmlTagWithMaxAttribute.max: String?
        get() = attributes[HtmlAttributes.MAX]
        set(value) = setAttribute(HtmlAttributes.MAX, value)

    public inline var HtmlTagWithLabelAttribute.label: String?
        get() = attributes[HtmlAttributes.LABEL]
        set(value) = setAttribute(HtmlAttributes.LABEL, value)

    public inline var HtmlTagWithAltAttribute.alt: String?
        get() = attributes[HtmlAttributes.ALT]
        set(value) = setAttribute(HtmlAttributes.ALT, value)

    public inline var HtmlTagWithCiteAttribute.cite: String?
        get() = attributes[HtmlAttributes.CITE]
        set(value) = setAttribute(HtmlAttributes.CITE, value)

    public inline var HtmlTagWithDateTimeAttribute.dateTime: String?
        get() = attributes[HtmlAttributes.DATETIME]
        set(value) = setAttribute(HtmlAttributes.DATETIME, value)

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

    public inline var HtmlTagWithDirnameAttribute.dirname: String?
        get() = attributes[HtmlAttributes.DIRNAME]
        set(value) = setAttribute(HtmlAttributes.DIRNAME, value)

    public inline var HtmlTagWithMaxLengthAttribute.maxLength: String?
        get() = attributes[HtmlAttributes.MAX_LENGTH]
        set(value) = setAttribute(HtmlAttributes.MAX_LENGTH, value)

    public inline var HtmlTagWithPlaceholderAttribute.placeholder: String?
        get() = attributes[HtmlAttributes.PLACEHOLDER]
        set(value) = setAttribute(HtmlAttributes.PLACEHOLDER, value)

    public inline var HtmlTagWithReadOnlyAttribute.readOnly: Boolean?
        get() = attributes[HtmlAttributes.READONLY]?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.READONLY, value)

    public inline var HtmlTagWithDownloadAttribute.download: String?
        get() = attributes[HtmlAttributes.DOWNLOAD]
        set(value) = setAttribute(HtmlAttributes.DOWNLOAD, value)

    public inline var HtmlTagWithHrefLangAttribute.hrefLang: String?
        get() = attributes[HtmlAttributes.HREF_LANG]
        set(value) = setAttribute(HtmlAttributes.HREF_LANG, value)

    public inline var HtmlTagWithColspanAttribute.colspan: String?
        get() = attributes[HtmlAttributes.COLSPAN]
        set(value) = setAttribute(HtmlAttributes.COLSPAN, value)

    public inline var HtmlTagWithHeadersAttribute.headers: String?
        get() = attributes[HtmlAttributes.HEADERS]
        set(value) = setAttribute(HtmlAttributes.HEADERS, value)

    public inline var HtmlTagWithRowspanAttribute.rowspan: String?
        get() = attributes[HtmlAttributes.ROWSPAN]
        set(value) = setAttribute(HtmlAttributes.ROWSPAN, value)

    public inline var HtmlTagWithCharsetAttribute.charset: String?
        get() = attributes[HtmlAttributes.CHARSET]
        set(value) = setAttribute(HtmlAttributes.CHARSET, value)

    public inline var HtmlTagWithAutocompleteAttribute.autocomplete: Boolean?
        get() = attributes[HtmlAttributes.AUTOCOMPLETE]?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.AUTOCOMPLETE, value)

    public inline var HtmlTagWithFormActionAttribute.formAction: String?
        get() = attributes[HtmlAttributes.FORM_ACTION]
        set(value) = setAttribute(HtmlAttributes.FORM_ACTION, value)

    public inline var HtmlTagWithPopoverTargetAttribute.popoverTarget: String?
        get() = attributes[HtmlAttributes.POPOVER_TARGET]
        set(value) = setAttribute(HtmlAttributes.POPOVER_TARGET, value)

    public inline var HtmlTagWithPopoverTargetActionAttribute.popoverTargetAction: String?
        get() = attributes[HtmlAttributes.POPOVER_TARGET_ACTION]
        set(value) = setAttribute(HtmlAttributes.POPOVER_TARGET_ACTION, value)

    public inline var HtmlTagWithMinAttribute.min: String?
        get() = attributes[HtmlAttributes.MIN]
        set(value) = setAttribute(HtmlAttributes.MIN, value)

    public inline var HtmlTagWithMultipleAttribute.multiple: String?
        get() = attributes[HtmlAttributes.MULTIPLE]
        set(value) = setAttribute(HtmlAttributes.MULTIPLE, value)

    public inline var HtmlTagWithSizeAttribute.size: String?
        get() = attributes[HtmlAttributes.SIZE]
        set(value) = setAttribute(HtmlAttributes.SIZE, value)

    public inline var HtmlTagWithSizesAttribute.sizes: String?
        get() = attributes[HtmlAttributes.SIZES]
        set(value) = setAttribute(HtmlAttributes.SIZES, value)

    public inline var HtmlTagWithUseMapAttribute.useMap: String?
        get() = attributes[HtmlAttributes.USE_MAP]
        set(value) = setAttribute(HtmlAttributes.USE_MAP, value)

    public inline var HtmlTagWithSrcsetAttribute.srcset: String?
        get() = attributes[HtmlAttributes.SRCSET]
        set(value) = setAttribute(HtmlAttributes.SRCSET, value)

    public inline var HtmlTagWithForAttribute.forId: String?
        get() = attributes[HtmlAttributes.FOR]
        set(value) = setAttribute(HtmlAttributes.FOR, value)

    public inline var HtmlTagWithSpanAttribute.span: String?
        get() = attributes[HtmlAttributes.SPAN]
        set(value) = setAttribute(HtmlAttributes.SPAN, value)

    public inline var HtmlTagWithReferrerPolicyAttribute.referrerPolicy: ReferrerPolicy?
        get() = attributes[HtmlAttributes.REFERRER_POLICY]?.let { ReferrerPolicy.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.REFERRER_POLICY, value?.value)

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
}
