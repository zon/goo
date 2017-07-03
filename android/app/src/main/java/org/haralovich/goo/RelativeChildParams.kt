package org.haralovich.goo

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.fasterxml.jackson.databind.JsonNode

class RelativeChildParams(json: JsonNode) : ChildParams<RelativeLayout.LayoutParams>(json) {
    var rightOf: Int? = null
    var leftOf: Int? = null
    var below: Int? = null
    var above: Int? = null
    var center = Axis.parse(json.path("center"))

    override fun link(parent: ViewGroup, index: Int, json: JsonNode) {
        rightOf = parseLink(parent, index, json.path("right-of"))
        leftOf = parseLink(parent, index, json.path("left-of"))
        below = parseLink(parent, index, json.path("below"))
        above = parseLink(parent, index, json.path("above"))
    }

    fun parseLink(parent: ViewGroup, index: Int, json: JsonNode): Int? {
        return when (json.textOption) {
            "previous" -> parent.getChildAt(index - 1)?.id
            "next" -> parent.getChildAt(index + 1)?.id
            else -> json.intOption?.let { parent.getChildAt(it)?.id }
        }
    }

    override fun export(context: Context): RelativeLayout.LayoutParams {
        val density = context.resources.displayMetrics.density
        val fit = RelativeLayout.LayoutParams.WRAP_CONTENT
        val fill = RelativeLayout.LayoutParams.MATCH_PARENT

        val w = if (left == null || right == null)
            width?.toValue(context) ?: fit
        else
            fill
        val h = if (top == null || bottom == null)
            height?.toValue(context) ?: fit
        else
            fill
        val params = RelativeLayout.LayoutParams(w, h)

        params.alignParentLeft = left != null && leftOf == null
        params.alignParentRight = right != null && rightOf == null
        params.alignParentTop = top != null && below == null
        params.alignParentBottom = bottom != null && above == null

        params.leftOf = leftOf ?: 0
        params.rightOf = rightOf ?: 0
        params.above = above ?: 0
        params.below = below ?: 0

        params.leftMargin = ((left ?: 0f) * density).toInt()
        params.rightMargin = ((right ?: 0f) * density).toInt()
        params.topMargin = ((top ?: 0f) * density).toInt()
        params.bottomMargin = ((bottom ?: 0f) * density).toInt()

        when (center) {
            Axis.HORIZONTAL -> {
                params.centerHorizontal = true
                params.centerVertical = false
                params.centerInParent = false
            }
            Axis.VERTICAL -> {
                params.centerHorizontal = false
                params.centerVertical = true
                params.centerInParent = false
            }
            Axis.BOTH -> {
                params.centerHorizontal = false
                params.centerVertical = false
                params.centerInParent = true
            }
        }

        return params
    }

}
