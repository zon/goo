package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

enum class Alignment {
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    CENTER_LEFT,
    CENTER,
    CENTER_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT;

    companion object {

        fun parse(json: JsonNode): Alignment? {
            return when(json.textOption) {
                "top-left" -> TOP_LEFT
                "top-center" -> TOP_CENTER
                "top-right" -> TOP_RIGHT
                "center-left" -> CENTER_LEFT
                "center" -> CENTER
                "center-right" -> CENTER_RIGHT
                "bottom-left" -> BOTTOM_LEFT
                "bottom-center" -> BOTTOM_CENTER
                "bottom-right" -> BOTTOM_RIGHT
                else -> null
            }
        }

    }

}
