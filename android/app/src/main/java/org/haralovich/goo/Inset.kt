package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

class Inset(
    val left: Float = 0f,
    val right: Float = 0f,
    val top: Float = 0f,
    val bottom: Float = 0f
) {

    companion object  {

        val zero = Inset()

        fun parse(json: JsonNode): Inset? {
            val left = json.path("left").floatOption
            val right = json.path("right").floatOption
            val top = json.path("top").floatOption
            val bottom = json.path("bottom").floatOption
            if (left != null || right != null || top != null || bottom != null) {
                return Inset(left ?: 0f, right ?: 0f, top ?: 0f, bottom ?: 0f)

            } else {
                return (
                    Vector.parse(json)?.let { Inset(it) } ?:
                    json.floatOption?.let { Inset(it) }
                )
            }
        }

    }

    constructor(value: Float) : this(value, value, value, value)

    constructor(edge: Vector) : this(edge.x, edge.x, edge.y, edge.y)

    operator fun plus(other: Inset): Inset {
        return Inset(left + other.left, right + other.right, top + other.top, bottom + other.bottom)
    }

    override fun toString(): String {
        return "Inset($left, $top, $right, $bottom)"
    }

}