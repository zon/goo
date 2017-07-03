package org.haralovich.goo

import android.content.Context
import android.view.ViewGroup
import com.fasterxml.jackson.databind.JsonNode

abstract class ChildLayout<out P: ViewGroup.LayoutParams>(
    var left: Float,
    var right: Float,
    var top: Float,
    var bottom: Float,
    var width: Measure?,
    var height: Measure?
) {

    fun parse(json: JsonNode) {
        left = json.path("left").floatValue()
        right = json.path("right").floatValue()
        top = json.path("top").floatValue()
        bottom = json.path("bottom").floatValue()
        width = Measure.parse(json.path("width"))
        height = Measure.parse(json.path("height"))
    }

    abstract fun export(context: Context): P

}