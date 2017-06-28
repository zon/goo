package org.haralovich.goo

import android.graphics.Color
import com.fasterxml.jackson.databind.JsonNode
import java.lang.IllegalArgumentException

val JsonNode.textOption: String?
    get() {
        if (isTextual) {
            return textValue()
        } else {
            return null
        }
    }

val JsonNode.intOption: Int?
    get() {
        if (isNumber) {
            return intValue()
        } else {
            return null
        }
    }

val JsonNode.floatOption: Float?
    get() {
        if (isNumber) {
            return floatValue()
        } else {
            return null
        }
    }

val JsonNode.booleanOption: Boolean?
    get() {
        if (isBoolean) {
            return booleanValue()
        } else {
            return null
        }
    }

val JsonNode.colorOption: Int?
    get() {
        var text = textOption
        if (text != null) {
            if (!text.startsWith('#'))
                text = '#' + text
            if (text.length == 4) {
                val r = text[1]
                val g = text[2]
                val b = text[3]
                text = "#$r$r$g$g$b$b"
            }
            try {
                return Color.parseColor(text)
            } catch (e: IllegalArgumentException) {
                return null
            }
        } else {
            return null
        }
    }