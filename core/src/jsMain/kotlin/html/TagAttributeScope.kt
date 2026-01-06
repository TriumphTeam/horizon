@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.attributes.CrossOrigin
import dev.triumphteam.horizon.html.attributes.HtmlAttributes
import dev.triumphteam.horizon.html.attributes.Preload
import dev.triumphteam.horizon.html.attributes.ReferrerPolicy
import dev.triumphteam.horizon.html.attributes.TagWithAltAttribute
import dev.triumphteam.horizon.html.attributes.TagWithAudioVideoAttributes
import dev.triumphteam.horizon.html.attributes.TagWithAutoFocusAttribute
import dev.triumphteam.horizon.html.attributes.TagWithAutocompleteAttribute
import dev.triumphteam.horizon.html.attributes.TagWithCharsetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithCiteAttribute
import dev.triumphteam.horizon.html.attributes.TagWithColspanAttribute
import dev.triumphteam.horizon.html.attributes.TagWithCrossOriginAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDateTimeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDirnameAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDisabledAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDownloadAttribute
import dev.triumphteam.horizon.html.attributes.TagWithForAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormActionAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHeadersAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHeightAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefLangAttribute
import dev.triumphteam.horizon.html.attributes.TagWithLabelAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMaxAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMaxLengthAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMediaAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMinAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMultipleAttribute
import dev.triumphteam.horizon.html.attributes.TagWithNameAttribute
import dev.triumphteam.horizon.html.attributes.TagWithPlaceholderAttribute
import dev.triumphteam.horizon.html.attributes.TagWithPopoverTargetActionAttribute
import dev.triumphteam.horizon.html.attributes.TagWithPopoverTargetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReadOnlyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRelAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRequiredAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRowspanAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSizeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSizesAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSpanAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSrcAttribute
import dev.triumphteam.horizon.html.attributes.TagWithSrcsetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTargetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTypeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithUseMapAttribute
import dev.triumphteam.horizon.html.attributes.TagWithValueAttribute
import dev.triumphteam.horizon.html.attributes.TagWithWidthAttribute
import dev.triumphteam.horizon.html.attributes.Target
import dev.triumphteam.horizon.html.attributes.getAttribute
import dev.triumphteam.horizon.html.attributes.setAttribute
import kotlinx.browser.document

public abstract class TagAttributeScope : Tag {

    public inline var Tag.className: String?
        get() = getAttribute(HtmlAttributes.CLASS)
        set(value) = setAttribute(HtmlAttributes.CLASS, value)

    public inline var Tag.accessKey: String?
        get() = getAttribute(HtmlAttributes.ACCESS_KEY)
        set(value) = setAttribute(HtmlAttributes.ACCESS_KEY, value)

    public inline var Tag.contentEditable: String?
        get() = getAttribute(HtmlAttributes.CONTENTEDITABLE)
        set(value) = setAttribute(HtmlAttributes.CONTENTEDITABLE, value)

    public inline var Tag.dir: String?
        get() = getAttribute(HtmlAttributes.DIR)
        set(value) = setAttribute(HtmlAttributes.DIR, value)

    public inline var Tag.draggable: Boolean?
        get() = getAttribute(HtmlAttributes.DRAGGABLE)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.DRAGGABLE, value)

    public inline var Tag.enterKeyHint: String?
        get() = getAttribute(HtmlAttributes.ENTER_KEY_HINT)
        set(value) = setAttribute(HtmlAttributes.ENTER_KEY_HINT, value)

    public inline var Tag.hidden: Boolean?
        get() = getAttribute(HtmlAttributes.HIDDEN)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.HIDDEN, value)

    public inline var Tag.id: String?
        get() = getAttribute(HtmlAttributes.ID)
        set(value) = setAttribute(HtmlAttributes.ID, value)

    public inline var Tag.inert: String?
        get() = getAttribute(HtmlAttributes.INERT)
        set(value) = setAttribute(HtmlAttributes.INERT, value)

    public inline var Tag.inputMode: String?
        get() = getAttribute(HtmlAttributes.INPUT_MODE)
        set(value) = setAttribute(HtmlAttributes.INPUT_MODE, value)

    public inline var Tag.lang: String?
        get() = getAttribute(HtmlAttributes.LANG)
        set(value) = setAttribute(HtmlAttributes.LANG, value)

    public inline var Tag.popover: String?
        get() = getAttribute(HtmlAttributes.POPOVER)
        set(value) = setAttribute(HtmlAttributes.POPOVER, value)

    public inline var Tag.spellcheck: String?
        get() = getAttribute(HtmlAttributes.SPELLCHECK)
        set(value) = setAttribute(HtmlAttributes.SPELLCHECK, value)

    public inline var Tag.style: String?
        get() = getAttribute(HtmlAttributes.STYLE)
        set(value) = setAttribute(HtmlAttributes.STYLE, value)

    public inline var Tag.tabindex: String?
        get() = getAttribute(HtmlAttributes.TAB_INDEX)
        set(value) = setAttribute(HtmlAttributes.TAB_INDEX, value)

    public inline var Tag.title: String?
        get() = getAttribute(HtmlAttributes.TITLE)
        set(value) = setAttribute(HtmlAttributes.TITLE, value)

    public inline var Tag.translate: String?
        get() = getAttribute(HtmlAttributes.TRANSLATE)
        set(value) = setAttribute(HtmlAttributes.TRANSLATE, value)

    public inline var TagWithNameAttribute.name: String?
        get() = getAttribute(HtmlAttributes.NAME)
        set(value) = setAttribute(HtmlAttributes.NAME, value)

    public inline var TagWithTypeAttribute.type: String?
        get() = getAttribute(HtmlAttributes.TYPE)
        set(value) = setAttribute(HtmlAttributes.TYPE, value)

    public inline var TagWithSrcAttribute.src: String?
        get() = getAttribute(HtmlAttributes.SRC)
        set(value) = setAttribute(HtmlAttributes.SRC, value)

    public inline var TagWithFormAttribute.form: String?
        get() = getAttribute(HtmlAttributes.FORM)
        set(value) = setAttribute(HtmlAttributes.FORM, value)

    public inline var TagWithDisabledAttribute.disabled: Boolean?
        get() = getAttribute(HtmlAttributes.DISABLED)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.DISABLED, value)

    public inline var TagWithHeightAttribute.height: Int?
        get() = getAttribute(HtmlAttributes.HEIGHT)?.toIntOrNull()
        set(value) = setAttribute(HtmlAttributes.HEIGHT, value?.toString())

    public inline var TagWithHeightAttribute.heightRaw: String?
        get() = getAttribute(HtmlAttributes.HEIGHT)
        set(value) = setAttribute(HtmlAttributes.HEIGHT, value)

    public inline var TagWithWidthAttribute.width: Int?
        get() = getAttribute(HtmlAttributes.WIDTH)?.toIntOrNull()
        set(value) = setAttribute(HtmlAttributes.WIDTH, value?.toString())

    public inline var TagWithWidthAttribute.widthRaw: String?
        get() = getAttribute(HtmlAttributes.WIDTH)
        set(value) = setAttribute(HtmlAttributes.WIDTH, value)

    public inline var TagWithValueAttribute.value: String?
        get() = getAttribute(HtmlAttributes.VALUE)
        set(value) = setAttribute(HtmlAttributes.VALUE, value)

    public inline var TagWithMediaAttribute.media: String?
        get() = getAttribute(HtmlAttributes.MEDIA)
        set(value) = setAttribute(HtmlAttributes.MEDIA, value)

    public inline var TagWithHrefAttribute.href: String?
        get() = getAttribute(HtmlAttributes.HREF)
        set(value) = setAttribute(HtmlAttributes.HREF, value)

    public inline var TagWithRelAttribute.rel: String?
        get() = getAttribute(HtmlAttributes.REL)
        set(value) = setAttribute(HtmlAttributes.REL, value)

    public inline var TagWithTargetAttribute.target: Target?
        get() = getAttribute(HtmlAttributes.TARGET)?.let { Target.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.TARGET, value?.value)

    public inline var TagWithTargetAttribute.targetRaw: String?
        get() = getAttribute(HtmlAttributes.TARGET)
        set(value) = setAttribute(HtmlAttributes.TARGET, value)

    public inline var TagWithAutoFocusAttribute.autoFocus: Boolean?
        get() = getAttribute(HtmlAttributes.AUTOFOCUS)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.AUTOFOCUS, value)

    public inline var TagWithRequiredAttribute.required: Boolean?
        get() = getAttribute(HtmlAttributes.REQUIRED)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.REQUIRED, value)

    public inline var TagWithMaxAttribute.max: String?
        get() = getAttribute(HtmlAttributes.MAX)
        set(value) = setAttribute(HtmlAttributes.MAX, value)

    public inline var TagWithLabelAttribute.label: String?
        get() = getAttribute(HtmlAttributes.LABEL)
        set(value) = setAttribute(HtmlAttributes.LABEL, value)

    public inline var TagWithAltAttribute.alt: String?
        get() = getAttribute(HtmlAttributes.ALT)
        set(value) = setAttribute(HtmlAttributes.ALT, value)

    public inline var TagWithCiteAttribute.cite: String?
        get() = getAttribute(HtmlAttributes.CITE)
        set(value) = setAttribute(HtmlAttributes.CITE, value)

    public inline var TagWithDateTimeAttribute.dateTime: String?
        get() = getAttribute(HtmlAttributes.DATETIME)
        set(value) = setAttribute(HtmlAttributes.DATETIME, value)

    public inline var TagWithAudioVideoAttributes.autoplay: Boolean?
        get() = getAttribute(HtmlAttributes.AUTOPLAY)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.AUTOPLAY, value)

    public inline var TagWithAudioVideoAttributes.controls: Boolean?
        get() = getAttribute(HtmlAttributes.CONTROLS)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.CONTROLS, value)

    public inline var TagWithAudioVideoAttributes.loop: Boolean?
        get() = getAttribute(HtmlAttributes.LOOP)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.LOOP, value)

    public inline var TagWithAudioVideoAttributes.muted: Boolean?
        get() = getAttribute(HtmlAttributes.MUTED)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.MUTED, value)

    public inline var TagWithAudioVideoAttributes.preload: Preload?
        get() = getAttribute(HtmlAttributes.PRELOAD)?.let { Preload.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.PRELOAD, value?.value)

    public inline var TagWithDirnameAttribute.dirname: String?
        get() = getAttribute(HtmlAttributes.DIRNAME)
        set(value) = setAttribute(HtmlAttributes.DIRNAME, value)

    public inline var TagWithMaxLengthAttribute.maxLength: String?
        get() = getAttribute(HtmlAttributes.MAX_LENGTH)
        set(value) = setAttribute(HtmlAttributes.MAX_LENGTH, value)

    public inline var TagWithPlaceholderAttribute.placeholder: String?
        get() = getAttribute(HtmlAttributes.PLACEHOLDER)
        set(value) = setAttribute(HtmlAttributes.PLACEHOLDER, value)

    public inline var TagWithReadOnlyAttribute.readOnly: Boolean?
        get() = getAttribute(HtmlAttributes.READONLY)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.READONLY, value)

    public inline var TagWithDownloadAttribute.download: String?
        get() = getAttribute(HtmlAttributes.DOWNLOAD)
        set(value) = setAttribute(HtmlAttributes.DOWNLOAD, value)

    public inline var TagWithHrefLangAttribute.hrefLang: String?
        get() = getAttribute(HtmlAttributes.HREF_LANG)
        set(value) = setAttribute(HtmlAttributes.HREF_LANG, value)

    public inline var TagWithColspanAttribute.colspan: String?
        get() = getAttribute(HtmlAttributes.COLSPAN)
        set(value) = setAttribute(HtmlAttributes.COLSPAN, value)

    public inline var TagWithHeadersAttribute.headers: String?
        get() = getAttribute(HtmlAttributes.HEADERS)
        set(value) = setAttribute(HtmlAttributes.HEADERS, value)

    public inline var TagWithRowspanAttribute.rowspan: String?
        get() = getAttribute(HtmlAttributes.ROWSPAN)
        set(value) = setAttribute(HtmlAttributes.ROWSPAN, value)

    public inline var TagWithCharsetAttribute.charset: String?
        get() = getAttribute(HtmlAttributes.CHARSET)
        set(value) = setAttribute(HtmlAttributes.CHARSET, value)

    public inline var TagWithAutocompleteAttribute.autocomplete: Boolean?
        get() = getAttribute(HtmlAttributes.AUTOCOMPLETE)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.AUTOCOMPLETE, value)

    public inline var TagWithFormActionAttribute.formAction: String?
        get() = getAttribute(HtmlAttributes.FORM_ACTION)
        set(value) = setAttribute(HtmlAttributes.FORM_ACTION, value)

    public inline var TagWithPopoverTargetAttribute.popoverTarget: String?
        get() = getAttribute(HtmlAttributes.POPOVER_TARGET)
        set(value) = setAttribute(HtmlAttributes.POPOVER_TARGET, value)

    public inline var TagWithPopoverTargetActionAttribute.popoverTargetAction: String?
        get() = getAttribute(HtmlAttributes.POPOVER_TARGET_ACTION)
        set(value) = setAttribute(HtmlAttributes.POPOVER_TARGET_ACTION, value)

    public inline var TagWithMinAttribute.min: String?
        get() = getAttribute(HtmlAttributes.MIN)
        set(value) = setAttribute(HtmlAttributes.MIN, value)

    public inline var TagWithMultipleAttribute.multiple: String?
        get() = getAttribute(HtmlAttributes.MULTIPLE)
        set(value) = setAttribute(HtmlAttributes.MULTIPLE, value)

    public inline var TagWithSizeAttribute.size: String?
        get() = getAttribute(HtmlAttributes.SIZE)
        set(value) = setAttribute(HtmlAttributes.SIZE, value)

    public inline var TagWithSizesAttribute.sizes: String?
        get() = getAttribute(HtmlAttributes.SIZES)
        set(value) = setAttribute(HtmlAttributes.SIZES, value)

    public inline var TagWithUseMapAttribute.useMap: String?
        get() = getAttribute(HtmlAttributes.USE_MAP)
        set(value) = setAttribute(HtmlAttributes.USE_MAP, value)

    public inline var TagWithSrcsetAttribute.srcset: String?
        get() = getAttribute(HtmlAttributes.SRCSET)
        set(value) = setAttribute(HtmlAttributes.SRCSET, value)

    public inline var TagWithForAttribute.forId: String?
        get() = getAttribute(HtmlAttributes.FOR)
        set(value) = setAttribute(HtmlAttributes.FOR, value)

    public inline var TagWithSpanAttribute.span: String?
        get() = getAttribute(HtmlAttributes.SPAN)
        set(value) = setAttribute(HtmlAttributes.SPAN, value)

    public inline var TagWithReferrerPolicyAttribute.referrerPolicy: ReferrerPolicy?
        get() = getAttribute(HtmlAttributes.REFERRER_POLICY)?.let { ReferrerPolicy.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.REFERRER_POLICY, value?.value)

    public inline var TagWithCrossOriginAttribute.crossOrigin: CrossOrigin?
        get() = getAttribute(HtmlAttributes.CROSS_ORIGIN)?.let { CrossOrigin.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.CROSS_ORIGIN, value?.value)

    /*public inline var ATag.ping: String?
        get() = getAttribute(HtmlAttributes.PING)
        set(value) = setAttribute(HtmlAttributes.PING, value)

    public inline var AreaTag.coords: String?
        get() = getAttribute(HtmlAttributes.COORDS)
        set(value) = setAttribute(HtmlAttributes.COORDS, value)

    public inline var AreaTag.shape: AreaShape?
        get() = getAttribute(HtmlAttributes.COORDS)?.let { AreaShape.valueOf(it) }
        set(value) = setAttribute(HtmlAttributes.COORDS, value?.value)

    public inline var AreaTag.shapeRaw: String?
        get() = getAttribute(HtmlAttributes.COORDS)
        set(value) = setAttribute(HtmlAttributes.COORDS, value)

    public inline var BlockQuoteTag.cite: String?
        get() = getAttribute(HtmlAttributes.CITE)
        set(value) = setAttribute(HtmlAttributes.CITE, value)

    public inline var MetaTag.content: String?
        get() = getAttribute(HtmlAttributes.CONTENT)
        set(value) = setAttribute(HtmlAttributes.CONTENT, value)

    public inline var MetaTag.httpEquiv: String?
        get() = getAttribute(HtmlAttributes.HTTP_EQUIV)
        set(value) = setAttribute(HtmlAttributes.HTTP_EQUIV, value)

    public inline var OlTag.reversed: String?
        get() = getAttribute(HtmlAttributes.REVERSED)
        set(value) = setAttribute(HtmlAttributes.REVERSED, value)

    public inline var OlTag.start: Int?
        get() = getAttribute(HtmlAttributes.START)?.toIntOrNull()
        set(value) = setAttribute(HtmlAttributes.START, value?.toString())

    public inline var ScriptTag.async: Boolean?
        get() = getAttribute(HtmlAttributes.ASYNC)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.ASYNC, value)

    public inline var ScriptTag.defer: Boolean?
        get() = getAttribute(HtmlAttributes.DEFER)?.toBoolean()
        set(value) = setAttribute(HtmlAttributes.DEFER, value)

    public inline var ScriptTag.integrity: String?
        get() = getAttribute(HtmlAttributes.INTEGRITY)
        set(value) = setAttribute(HtmlAttributes.INTEGRITY, value)*/
}
