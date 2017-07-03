package org.haralovich.goo

import android.content.Context
import android.view.ViewGroup
import com.fasterxml.jackson.databind.JsonNode

abstract class ChildParams<out P: ViewGroup.LayoutParams>(json: JsonNode) {
    var left = json.path("left").floatOption
    var right = json.path("right").floatOption
    var top = json.path("top").floatOption
    var bottom = json.path("bottom").floatOption
    var width = Measure.parse(json.path("width"))
    var height = Measure.parse(json.path("height"))

    open fun link(parent: ViewGroup, index: Int, json: JsonNode) {}

    abstract fun export(context: Context): P

}