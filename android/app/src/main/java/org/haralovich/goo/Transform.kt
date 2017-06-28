package org.haralovich.goo

import com.fasterxml.jackson.databind.JsonNode

class Transform(
    val anchor: Anchor = Anchor.fallback,
    val origin: Vector? = null,
    val left: Float = 0f,
    val right: Float = 0f,
    val top: Float = 0f,
    val bottom: Float = 0f,
    val width: Float = 0f,
    val height: Float = 0f
) {

    companion object {

        fun parse(json: JsonNode): Transform {

            val anchorNode = json.path("anchor")
            val anchor = (
                anchorNode.textOption?.let { Anchor.strings[it] } ?:
                Anchor.parse(anchorNode) ?:
                Anchor.fallback
            )

            val originNode = json.path("origin")
            val origin = if (originNode.textOption == "center") {
                Vector.half
            } else {
                Vector.parse(originNode)
            }

            return Transform(
                anchor,
                origin,
                json.path("left").floatOption ?: json.path("x").floatValue(),
                json.path("right").floatValue(),
                json.path("top").floatOption ?: json.path("y").floatValue(),
                json.path("bottom").floatValue(),
                json.path("width").floatValue(),
                json.path("height").floatValue()
            )
        }

    }

    constructor(size: Vector) : this(width = size.x, height = size.y)

    fun export(within: Rect): Inset {
        var x = 0f
        var y = 0f
        var w = 0f
        var h = 0f
        val origin = this.origin ?: Anchor.defaultOrigins.get(anchor) ?: Anchor.originFallback
        val bounds = anchor * within.size

        if (anchor.xCollapsed) {
            x = bounds.min.x + left - right - width * origin.x
            w = width

        } else {
            x = bounds.min.x + left
            w = bounds.max.x - bounds.min.x - left - right
        }

        if (anchor.yCollapsed) {
            y = bounds.min.y + top - bottom - height * origin.y
            h = height

        } else {
            y = bounds.min.y + top
            h = bounds.max.y - bounds.min.y - top - bottom
        }

        val left = within.origin.x + x
        val top = within.origin.y + y
        return Inset(
            left = left,
            right = within.size.x - left - w,
            top = top,
            bottom = within.size.y - top - h
        )
    }

}
