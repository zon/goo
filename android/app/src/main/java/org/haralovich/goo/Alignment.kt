package org.haralovich.goo

import android.view.Gravity
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

    fun export(): Int {
        return when (this) {
            Alignment.TOP_LEFT -> Gravity.TOP and Gravity.LEFT
            Alignment.TOP_CENTER -> Gravity.TOP and Gravity.CENTER_HORIZONTAL
            Alignment.TOP_RIGHT -> Gravity.TOP and Gravity.RIGHT
            Alignment.CENTER_LEFT -> Gravity.CENTER_VERTICAL and Gravity.LEFT
            Alignment.CENTER -> Gravity.CENTER
            Alignment.CENTER_RIGHT -> Gravity.CENTER_VERTICAL and Gravity.RIGHT
            Alignment.BOTTOM_LEFT -> Gravity.BOTTOM and Gravity.LEFT
            Alignment.BOTTOM_CENTER -> Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL
            Alignment.BOTTOM_RIGHT -> Gravity.BOTTOM and Gravity.RIGHT
        }
    }

    fun exportText(): Int {
        return when (this) {
            Alignment.TOP_LEFT -> Gravity.TOP and Gravity.START
            Alignment.TOP_CENTER -> Gravity.TOP and Gravity.CENTER_HORIZONTAL
            Alignment.TOP_RIGHT -> Gravity.TOP and Gravity.END
            Alignment.CENTER_LEFT -> Gravity.CENTER_VERTICAL and Gravity.START
            Alignment.CENTER -> Gravity.CENTER
            Alignment.CENTER_RIGHT -> Gravity.CENTER_VERTICAL and Gravity.END
            Alignment.BOTTOM_LEFT -> Gravity.BOTTOM and Gravity.START
            Alignment.BOTTOM_CENTER -> Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL
            Alignment.BOTTOM_RIGHT -> Gravity.BOTTOM and Gravity.END
        }
    }

}
