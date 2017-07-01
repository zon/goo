package org.haralovich.goo

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

class LinearChild(
    left: Float,
    right: Float,
    top: Float,
    bottom: Float,
    width: Float?,
    height: Float?,
    val alignment: Alignment,
    val weight: Float
) : ChildProps<LinearLayout.LayoutParams>(left, right, top, bottom, width, height) {

    companion object {

        fun parse(json: JsonNode): LinearChild {
            return LinearChild(
                json.path("left").floatValue(),
                json.path("right").floatValue(),
                json.path("top").floatValue(),
                json.path("bottom").floatValue(),
                json.path("width").floatOption,
                json.path("height").floatOption,
                Alignment.parse(json.path("alignment")) ?: Alignment.CENTER,
                json.path("weight").floatOption ?: 1f
            )
        }

    }

    override fun export(context: Context, inset: Inset): LinearLayout.LayoutParams {
        val density = context.resources.displayMetrics.density
        val fit = LinearLayout.LayoutParams.WRAP_CONTENT
        val w = width?.let { (it * density).toInt() } ?: fit
        val h = height?.let { (it * density).toInt() } ?: fit
        val params = LinearLayout.LayoutParams(w, h)
        params.leftMargin = ((left + inset.left) * density).toInt()
        params.rightMargin = ((right + inset.right) * density).toInt()
        params.topMargin = ((top + inset.top) * density).toInt()
        params.bottomMargin = ((bottom + inset.bottom) * density).toInt()
        params.gravity = when (alignment) {
            Alignment.TOP_LEFT -> Gravity.TOP and Gravity.LEFT
            Alignment.TOP_CENTER -> Gravity.TOP and Gravity.CENTER_HORIZONTAL
            Alignment.TOP_RIGHT -> Gravity.TOP and Gravity.RIGHT
            Alignment.CENTER_LEFT -> Gravity.CENTER_VERTICAL and Gravity.LEFT
            Alignment.CENTER -> Gravity.CENTER
            Alignment.CENTER_RIGHT -> Gravity.CENTER_VERTICAL and Gravity.RIGHT
            Alignment.BOTTOM_LEFT -> Gravity.BOTTOM and Gravity.LEFT
            Alignment.BOTTOM_CENTER -> Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL
            Alignment.BOTTOM_RIGHT -> Gravity.BOTTOM and Gravity.RIGHT
        }
        params.weight = weight
        return params
    }

}
