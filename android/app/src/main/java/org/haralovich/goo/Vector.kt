package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

class Vector(var x: Float, var y: Float) {

    companion object {
        val zero = Vector(0f, 0f)
        val half = Vector(0.5f, 0.5f)
        val one = Vector(1f, 1f)
        val left = Vector(1f, 0f)
        val up = Vector(0f, 1f)

        fun parse(json: JsonNode): Vector? {
            val ax = json.path(0).floatOption
            val ay = json.path(1).floatOption
            if (ax != null && ay != null) {
                return Vector(ax, ay)
            } else {
                val dx = json.path("x").floatOption
                val dy = json.path("y").floatOption
                if (dx != null || dy != null) {
                    return Vector(dx ?: 0f, dy ?: 0f)
                } else {
                    return null
                }
            }
        }

        fun direction(json: JsonNode): Vector {
            return when (json.textOption) {
                "vertical" -> up
                "horizontal" -> left
                "both" -> one
                else -> zero
            }
        }

    }

    override fun equals(other: Any?): Boolean {
        if (other is Vector) {
            return other.x == x && other.y == y
        } else {
            return false
        }
    }

    operator fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector): Vector {
        return Vector(x - other.x, y - other.y)
    }

    operator fun times(other: Vector): Vector {
        return Vector(x * other.x, y * other.y)
    }

    operator fun times(other: Float): Vector {
        return Vector(x * other, y * other)
    }

    override fun toString(): String {
        return "Vector($x, $y)"
    }

    operator fun times(other: Float?): Vector? {
        return other?.let { this * it }
    }

    operator fun  times(other: Int): Vector {
        return this * other.toFloat()
    }

}