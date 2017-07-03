package org.haralovich.goo

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

class Element(context: Context, json: JsonNode, parent: Element? = null) {
    val type = ViewType.parse(json.path("type")) ?: ViewType.RELATIVE

    val props = type.exportProps(json)

    var params = when(parent?.view) {
        is LinearLayout -> LinearChildParams(json)
        else -> RelativeChildParams(json)
    }

    var background = json.path("background").colorOption

    val view = type.export(context)

    var children: Set<Element>

    init {
        view.id = View.generateViewId()

        val childrenJson = json.path("children")
        children = childrenJson.map { Element(context, it, this) }.toSet()
        if (view is ViewGroup) {
            for (child in children) {
                view.addView(child.view)
            }
            for ((index, child) in children.withIndex()) {
                child.params.link(view, index, childrenJson[index])
            }
        }
    }

    companion object {

        val TAG = "Element"

        fun root(context: Context, json: JsonNode): Element {
            val root = Element(context, json)
            root.params.left = 0f
            root.params.top = 0f
            root.params.right = 0f
            root.params.bottom = 0f
            return root
        }

    }

    fun update() {
        props.update(view)

        view.layoutParams = params.export(view.context)

        view.setBackgroundColor(background ?: 0)

        for (child in children) {
            child.update()
        }
    }

}