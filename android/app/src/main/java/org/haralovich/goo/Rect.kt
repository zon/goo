package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

class Rect(var origin: Vector, var size: Vector) {

    companion object {

        fun parse(json: JsonNode): Rect? {
            val origin = Vector.parse(json)
            val size = Vector.parse(json)
            if (origin != null || size != null) {
                return Rect(origin ?: Vector.zero, size ?: Vector.zero)
            } else {
                return null
            }
        }

    }

    constructor(
        x: Float = 0f,
        y: Float = 0f,
        width: Float = 0f,
        height: Float = 0f
    ) : this (Vector(x, y), Vector(width, height))

    operator fun plus(inset: Inset): Rect {
        val lt = Vector(inset.left, inset.top)
        val rb = Vector(inset.right, inset.bottom)
        return Rect(origin + lt, size - lt - rb)
    }

}