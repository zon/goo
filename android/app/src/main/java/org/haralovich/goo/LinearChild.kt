package org.haralovich.goo

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

class LinearChild(
    left: Float = 0f,
    right: Float = 0f,
    top: Float = 0f,
    bottom: Float = 0f,
    width: Measure? = null,
    height: Measure? = null,
    var alignment: Alignment = alignmentFallback,
    var weight: Float = weightFallback
) : ChildProps<LinearLayout.LayoutParams>(left, right, top, bottom, width, height) {

    companion object {

        val alignmentFallback = Alignment.CENTER
        val weightFallback = 1f

        fun parse(json: JsonNode): LinearChild {
            val props = LinearChild(
                alignment = Alignment.parse(json.path("alignment")) ?: alignmentFallback,
                weight = json.path("weight").floatOption ?: weightFallback
            )
            props.parse(json)
            return props
        }

    }

    override fun export(context: Context): LinearLayout.LayoutParams {
        val density = context.resources.displayMetrics.density
        val fit = LinearLayout.LayoutParams.WRAP_CONTENT
        val w = width?.let { it.toValue(context) } ?: fit
        val h = height?.let { it.toValue(context) } ?: fit
        val params = LinearLayout.LayoutParams(w, h)
        params.leftMargin = (left * density).toInt()
        params.rightMargin = (right * density).toInt()
        params.topMargin = (top * density).toInt()
        params.bottomMargin = (bottom * density).toInt()
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
