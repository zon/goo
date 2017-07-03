package org.haralovich.goo

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.fasterxml.jackson.databind.JsonNode

class Element(
        context: Context,
        val id: String?,
        val type: ViewType,
        val self: SelfLayout,
        val child: ChildLayout<ViewGroup.LayoutParams>,
        var background: Int?,
        var children: Set<Element> = emptySet()
) {
    val view: View

    init {
        view = type.export(context)
    }

    companion object {

        val TAG = "Element"

        fun parse(context: Context, json: JsonNode, parent: Element? = null): Element {

            val type = ViewType.parse(json.path("type")) ?: ViewType.RELATIVE

            val self: SelfLayout = when (type) {
                ViewType.RELATIVE -> SelfLayout.parse(json)
                ViewType.VERTICAL -> LinearSelf.parse(LinearDirection.VERTICAL, json)
                ViewType.HORIZONTAL -> LinearSelf.parse(LinearDirection.HORIZONTAL, json)
                ViewType.LABEL -> LabelSelf.parse(json)
            }

            val child = when(parent?.type) {
                ViewType.RELATIVE -> RelativeChild.parse(json)
                ViewType.VERTICAL -> LinearChild.parse(json)
                ViewType.HORIZONTAL -> LinearChild.parse(json)
                else -> RelativeChild.parse(json)
            }

            val element = Element(
                context = context,
                id = json.path("id").textOption,
                type = type,
                self = self,
                child = child,
                background = json.path("background").colorOption
            )
            parseChildren(element, json)
            return element
        }

        fun root(context: Context, json: JsonNode): Element {
            val element = Element(
                context,
                json.path("id").textOption,
                ViewType.parse(json.path("layout")) ?: ViewType.RELATIVE,
                SelfLayout.parse(json),
                RelativeChild.parse(json, fallback = Anchor.FILL),
                json.path("background").colorOption
            )
            parseChildren(element, json)
            return element
        }

        private fun parseChildren(parent: Element, json: JsonNode) {
            parent.children = json
                .path("children")
                .map { Element.parse(parent.view.context, it, parent) }
                .toSet()
            if (parent.view is ViewGroup) {
                for (child in parent.children) {
                    parent.view.addView(child.view)
                }
            }
        }

    }

    fun update() {
        self.update(view)

        View.generateViewId()

        view.setBackgroundColor(background ?: 0)

        view.layoutParams = child.export(view.context)

        for (child in children) {
            child.update()
        }
    }

}