package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

enum class Axis(val key: String) {
    HORIZONTAL("horizontal"),
    VERTICAL("vertical"),
    BOTH("both");

    companion object {

        fun parse(json: JsonNode): Axis? {
            val key = json.textOption
            if (key != null)
                return enumValues<Axis>().find { it.key == key }
            else
                return null
        }

    }

}