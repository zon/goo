package org.haralovich.goo

import android.content.Context
import android.widget.RelativeLayout
import com.fasterxml.jackson.databind.JsonNode

class Transform(
    val anchor: Anchor = Anchor.fallback,
    val left: Float = 0f,
    val right: Float = 0f,
    val top: Float = 0f,
    val bottom: Float = 0f,
    val width: Float = 0f,
    val height: Float = 0f
) {

    companion object {

        fun parse(json: JsonNode): Transform {
            return Transform(
                Anchor.parse(json.path("anchor")) ?: Anchor.fallback,
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

    fun export(context: Context, inset: Inset): RelativeLayout.LayoutParams {
        val density = context.resources.displayMetrics.density
        val w = (width * density).toInt()
        val h = (height * density).toInt()
        val l = ((left + inset.left) * density).toInt()
        val r = ((right + inset.right) * density).toInt()
        val t = ((top + inset.top) * density).toInt()
        val b = ((bottom + inset.bottom) * density).toInt()
        val fill = RelativeLayout.LayoutParams.MATCH_PARENT
//        val fit = RelativeLayout.LayoutParams.WRAP_CONTENT

        val params = RelativeLayout.LayoutParams(w, h)

        when (anchor) {
            Anchor.TOP_LEFT -> {
                params.alignParentTop = true
                params.topMargin = t
                params.alignParentLeft = true
                params.leftMargin = l
            }
            Anchor.TOP_CENTER -> {
                params.alignParentTop = true
                params.topMargin = t
                params.centerHorizontal = true
            }
            Anchor.TOP_RIGHT -> {
                params.alignParentTop = true
                params.topMargin = t
                params.alignParentRight = true
                params.rightMargin = r
            }
            Anchor.CENTER_LEFT -> {
                params.centerVertical = true
                params.alignParentLeft = true
                params.leftMargin = l
            }
            Anchor.CENTER -> {
                params.centerInParent = true
            }
            Anchor.CENTER_RIGHT -> {
                params.centerHorizontal = true
                params.alignParentRight = true
                params.rightMargin = r
            }
            Anchor.BOTTOM_LEFT -> {
                params.alignParentBottom = true
                params.alignParentLeft = true
                params.leftMargin = l
            }
            Anchor.BOTTOM_CENTER -> {
                params.alignParentBottom = true
                params.bottomMargin = b
                params.centerHorizontal = true
            }
            Anchor.BOTTOM_RIGHT -> {
                params.alignParentBottom = true
                params.bottomMargin = b
                params.alignParentRight = true
                params.rightMargin = r
            }
            Anchor.TOP_FILL -> {
                params.alignParentTop = true
                params.topMargin = t
                params.alignParentLeft = true
                params.leftMargin = l
                params.alignParentRight = true
                params.rightMargin = r
                params.width = fill
            }
            Anchor.HORIZONTAL_FILL -> {
                params.alignParentLeft = true
                params.leftMargin = l
                params.alignParentRight = true
                params.rightMargin = r
                params.centerVertical = true
                params.width = fill
            }
            Anchor.BOTTOM_FILL -> {
                params.alignParentBottom = true
                params.bottomMargin = b
                params.alignParentLeft = true
                params.leftMargin = l
                params.alignParentRight = true
                params.rightMargin = r
                params.width = fill
            }
            Anchor.LEFT_FILL -> {
                params.alignParentLeft = true
                params.leftMargin = l
                params.alignParentTop = true
                params.topMargin = t
                params.alignParentBottom = true
                params.bottomMargin = b
                params.height = fill
            }
            Anchor.VERTICAL_FILL -> {
                params.alignParentTop = true
                params.topMargin = t
                params.alignParentBottom = true
                params.bottomMargin = b
                params.centerHorizontal = true
                params.height = fill
            }
            Anchor.RIGHT_FILL -> {
                params.alignParentRight = true
                params.rightMargin = r
                params.alignParentTop = true
                params.topMargin = t
                params.alignParentBottom = true
                params.bottomMargin = b
                params.height = fill
            }
            Anchor.FILL -> {
                params.alignParentLeft = true
                params.alignParentTop = true
                params.alignParentRight = true
                params.alignParentBottom = true
                params.width = fill
                params.height = fill
            }
        }

        return params
    }

}
