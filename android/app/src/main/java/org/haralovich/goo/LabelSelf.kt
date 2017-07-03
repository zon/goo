package org.haralovich.goo

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import com.fasterxml.jackson.databind.JsonNode

class LabelSelf(
    var font: String? = null,
    var fontSize: Float = fontSizeFallback,
    var color: Int = colorFallback,
    var alignment: Alignment = alignmentFallback,
    var text: String? = null,
    padding: Inset = Inset.zero
) : SelfLayout(padding) {

    companion object {

        val fontSizeFallback = 16f
        val colorFallback = Color.BLACK
        val alignmentFallback = Alignment.CENTER_LEFT

        fun parse(json: JsonNode): LabelSelf {
            val label = LabelSelf()
            label.parse(json)
            return label
        }

    }

    override fun parse(json: JsonNode) {
        super.parse(json)
        font = json.path("font").textOption
        fontSize = json.path("font-size").floatOption ?: fontSizeFallback
        color = json.path("color").colorOption ?: colorFallback
        alignment = Alignment.parse(json.path("text-alignment")) ?: alignmentFallback
        text = json.path("text").textOption
    }

    override fun update(view: View) {
        super.update(view)
        if (view is TextView) {
            if (font != null)
                view.typeface = Typeface.create(font, Typeface.NORMAL)
            view.textSize = fontSize
            view.setTextColor(color)
            view.gravity = alignment.exportText()
            view.text = text
        }
    }

}