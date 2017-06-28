package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

open class Bounds(val min: Vector, val max: Vector) {

    companion object {

        fun parse(json: JsonNode): Bounds? {
            val min = Vector.parse(json.path("min"))
            val max = Vector.parse(json.path("max"))
            if (min != null && max != null) {
                return Bounds(min, max)
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
    ) : this (Vector(minX, minY), Vector(maxX, maxY))

    override fun equals(other: Any?): Boolean {
        if (other is Bounds) {
            return other.min == min && other.max == max
        } else {
            return false
        }
    }

    operator fun plus(inset: Inset): Bounds {
        return Bounds(
            min.x + inset.left,
            min.y + inset.top,
            max.x - inset.right,
            max.y - inset.bottom
        )
    }

    operator fun times(other: Vector): Bounds {
        return Bounds(min * other, max * other)
    }

    override fun toString(): String {
        return "Bounds(${min.x}, ${min.y}, ${max.x}, ${max.y})"
    }

}
