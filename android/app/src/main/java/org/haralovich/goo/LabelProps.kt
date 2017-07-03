package org.haralovich.goo

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import com.fasterxml.jackson.databind.JsonNode

class LabelProps(json: JsonNode) : ViewProps(json) {
    var font = json.path("font").textOption
    var fontSize = json.path("font-size").floatOption ?: 16f
    var color = json.path("color").colorOption ?: Color.BLACK
    var alignment = Alignment.parse(json.path("text-alignment")) ?: Alignment.CENTER_LEFT
    var text = json.path("text").textOption

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