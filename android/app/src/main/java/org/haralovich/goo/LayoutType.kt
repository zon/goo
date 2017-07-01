package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

enum class LayoutType {
    RELATIVE,
    VERTICAL,
    HORIZONTAL;

    companion object {

        fun parse(json: JsonNode): LayoutType? {
            return when (json.textOption) {
                "relative" -> RELATIVE
                "horizontal" -> HORIZONTAL
                "vertical" -> VERTICAL
                else -> null
            }
        }

    }

}
