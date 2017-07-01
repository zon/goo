package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

enum class Anchor(val key: String) {
    TOP_LEFT("top-left"),
    TOP_CENTER("top-center"),
    TOP_RIGHT("top-right"),
    CENTER_LEFT("center-left"),
    CENTER("center"),
    CENTER_RIGHT("center-right"),
    BOTTOM_LEFT("bottom-left"),
    BOTTOM_CENTER("bottom-center"),
    BOTTOM_RIGHT("bottom-right"),
    TOP_FILL("top-fill"),
    HORIZONTAL_FILL("horizontal-fill"),
    BOTTOM_FILL("bottom-fill"),
    LEFT_FILL("left-fill"),
    VERTICAL_FILL("vertical-fill"),
    RIGHT_FILL("right-fill"),
    FILL("fill");

    companion object {

        val fallback = TOP_FILL

        fun parse(json: JsonNode): Anchor? {
            val key = json.textOption
            if (key != null)
                return enumValues<Anchor>().find { it.key == key }
            else
                return null
        }

    }

}
