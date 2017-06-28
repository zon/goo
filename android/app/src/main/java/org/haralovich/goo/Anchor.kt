package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

class Anchor(min: Vector, max: Vector) : Bounds(min, max) {

    companion object {
        val topLeft = Anchor()
        val topCenter = Anchor(minX = 0.5f, maxX = 0.5f)
        val topRight = Anchor(minX = 1f, maxX = 1f)
        val centerLeft = Anchor(minY = 0.5f, maxY = 0.5f)
        val center = Anchor(minX = 0.5f, minY = 0.5f, maxX = 0.5f, maxY = 0.5f)
        val centerRight = Anchor(minX = 1f, minY = 0.5f, maxX = 1f, maxY = 0.5f)
        val bottomLeft = Anchor(minY = 1f, maxY = 1f)
        val bottomCenter = Anchor(minX = 0.5f, minY = 1f, maxX = 0.5f, maxY = 1f)
        val bottomRight = Anchor(minX = 1f, minY = 1f, maxX = 1f, maxY = 1f)
        val topFill = Anchor(maxX = 1f)
        val horizontalFill = Anchor(minY = 0.5f, maxX = 1f, maxY = 0.5f)
        val bottomFill = Anchor(minY = 1f, maxX = 1f, maxY = 1f)
        val leftFill = Anchor(maxY = 1f)
        val verticalFill = Anchor(minX = 0.5f, maxX = 0.5f, maxY = 1f)
        val rightFill = Anchor(minX = 1f, maxX = 1f, maxY = 1f)
        val fill = Anchor(maxX = 1f, maxY = 1f)

        val fallback = topFill
        val originFallback = Vector.zero

        val strings = mapOf(
            "top-left" to topLeft,
            "top-center" to topCenter,
            "top-right" to topRight,
            "center-left" to centerLeft,
            "center" to center,
            "center-right" to centerRight,
            "bottom-left" to bottomLeft,
            "bottom-center" to bottomCenter,
            "bottom-right" to bottomRight,
            "top-fill" to topFill,
            "horizontal-fill" to horizontalFill,
            "bottom-fill" to bottomFill,
            "left-fill" to leftFill,
            "vertical-fill" to verticalFill,
            "right-fill" to rightFill,
            "fill" to fill
        )

        val defaultOrigins = mapOf(
            topLeft to Vector(0f, 0f),
            topCenter to Vector(0.5f, 0f),
            topRight to Vector(1f, 0f),
            centerLeft to Vector(0f, 0.5f),
            center to Vector(0.5f, 0.5f),
            centerRight to Vector(1f, 0.5f),
            bottomLeft to Vector(0f, 1f),
            bottomCenter to Vector(0.5f, 1f),
            bottomRight to Vector(1f, 1f),
            topFill to Vector(0f, 0f),
            horizontalFill to Vector(0f, 0.5f),
            bottomFill to Vector(0f, 1f),
            leftFill to Vector(0f, 0f),
            verticalFill to Vector(0.5f, 0f),
            rightFill to Vector(1f, 0f),
            fill to Vector(0f, 0f)
        )

        fun parse(json: JsonNode): Anchor? {
            val min = Vector.parse(json.path("min"))
            val max = Vector.parse(json.path("max"))
            if (min != null && max != null) {
                return Anchor(min, max)
            } else {
                return null
            }
        }

    }

    constructor(
        minX: Float = 0f,
        minY: Float = 0f,
        maxX: Float = 0f,
        maxY: Float = 0f
    ) : this(Vector(minX, minY), Vector(maxX, maxY))

    val xCollapsed: Boolean
        get() = min.x == max.x

    val yCollapsed: Boolean
        get() = min.y == max.y

}
