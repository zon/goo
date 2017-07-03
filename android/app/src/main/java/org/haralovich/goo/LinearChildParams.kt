package org.haralovich.goo

import android.content.Context
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

class LinearChildParams(json: JsonNode) : ChildParams<LinearLayout.LayoutParams>(json) {
    var alignment = Alignment.parse(json.path("alignment")) ?: Alignment.CENTER
    var weight = json.path("weight").floatOption ?: 1f

    override fun export(context: Context): LinearLayout.LayoutParams {
        val density = context.resources.displayMetrics.density
        val fit = LinearLayout.LayoutParams.WRAP_CONTENT
        val w = width?.toValue(context) ?: fit
        val h = height?.toValue(context) ?: fit
        val params = LinearLayout.LayoutParams(w, h)
        params.leftMargin = ((left ?: 0f) * density).toInt()
        params.rightMargin = ((right ?: 0f) * density).toInt()
        params.topMargin = ((top ?: 0f) * density).toInt()
        params.bottomMargin = ((bottom ?: 0f) * density).toInt()
        params.gravity = alignment.export()
        params.weight = weight
        return params
    }

}
