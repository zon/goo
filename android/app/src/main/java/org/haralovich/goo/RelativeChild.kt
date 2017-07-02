package org.haralovich.goo

import android.content.Context
import android.widget.RelativeLayout
import com.fasterxml.jackson.databind.JsonNode

class RelativeChild(
    val anchor: Anchor,
    left: Float,
    right: Float,
    top: Float,
    bottom: Float,
    width: Float?,
    height: Float?
) : ChildProps<RelativeLayout.LayoutParams>(left, right, top, bottom, width, height) {

    companion object {

        fun parse(json: JsonNode, fallback: Anchor = Anchor.TOP_FILL): RelativeChild {
            return RelativeChild(
                Anchor.parse(json.path("anchor")) ?: fallback,
                json.path("left").floatValue(),
                json.path("right").floatValue(),
                json.path("top").floatValue(),
                json.path("bottom").floatValue(),
                json.path("width").floatOption,
                json.path("height").floatOption
            )
        }

    }

    override fun export(context: Context): RelativeLayout.LayoutParams {
        val density = context.resources.displayMetrics.density
        val fit = RelativeLayout.LayoutParams.WRAP_CONTENT
        val fill = RelativeLayout.LayoutParams.MATCH_PARENT
        val w = width?.let { (it * density).toInt() } ?: fit
        val h = height?.let { (it * density).toInt() } ?: fit
        val l = (left * density).toInt()
        val r = (right * density).toInt()
        val t = (top * density).toInt()
        val b = (bottom * density).toInt()

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
