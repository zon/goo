package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.NullNode

enum class LayoutType {
    RELATIVE,
    VERTICAL,
    HORIZONTAL,
    GRID;

    companion object {

        fun parse(json: JsonNode): LayoutType? {
            return when (json.textOption) {
                "relative" -> RELATIVE
                "horizontal" -> HORIZONTAL
                "grid" -> GRID
                "vertical" -> VERTICAL
                else -> null
            }
        }

    }

}

class Layout(
    val type: LayoutType,
    val padding: Inset,
    val spacing: Vector,
    val alignment: Alignment,
    val cellSize: Vector?,
    val distribute: Vector,
    val fit: Vector,
    val weight: Float,
    val ignore: Boolean
) {

    companion object {

        fun parse(json: JsonNode): Layout {
            val type = LayoutType.parse(json.path("type")) ?: LayoutType.VERTICAL

            val paddingProp = json.path("padding")
            val padding = if (paddingProp.isFloat) {
                Inset(paddingProp.floatValue())
            } else {
                Inset.parse(paddingProp) ?: Inset.zero
            }

            val spacingProp = json.path("spacing")
            val spacing = if (spacingProp.isFloat) {
                Vector.one * spacingProp.floatValue()
            } else {
                Vector.parse(spacingProp) ?: Vector.zero
            }

            val alignment = Alignment.parse(json.path("alignment")) ?: Alignment.TOP_LEFT

            val cellSizeProp = json.path("cell-size")
            val cellSize = Vector.one * cellSizeProp.floatOption ?: Vector.parse(cellSizeProp)

            val distribute = Vector.direction(json.path("distribute"))

            val fit = Vector.direction(json.path("fit"))

            val weight = json.path("weight").floatOption ?: 1f

            val ignore = json.path("ignore-layout").booleanValue()

            return Layout(
                type = type,
                padding = padding,
                spacing = spacing,
                alignment = alignment,
                cellSize = cellSize,
                distribute = distribute,
                fit = fit,
                weight = weight,
                ignore = ignore
            )
        }

    }

}
