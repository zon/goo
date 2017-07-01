package org.haralovich.goo

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.fasterxml.jackson.databind.JsonNode

class Element(
        context: Context,
        val id: String?,
        val type: LayoutType,
        val self: SelfProps,
        val child: ChildProps<ViewGroup.LayoutParams>,
        val background: Int?,
        var children: Set<Element> = emptySet()
) {
    val view: View

    init {
        view = when (type) {
            LayoutType.RELATIVE -> RelativeLayout(context)
            LayoutType.VERTICAL -> LinearLayout(context)
            LayoutType.HORIZONTAL -> LinearLayout(context)
        }
    }

    companion object {

        val TAG = "Element"

        fun parse(context: Context, json: JsonNode, parent: Element? = null): Element {

            val type = LayoutType.parse(json.path("layout")) ?: LayoutType.RELATIVE

            val self: SelfProps = when (type) {
                LayoutType.RELATIVE -> SelfProps.parse(json)
                LayoutType.VERTICAL -> LinearParent.parse(LinearDirection.VERTICAL, json)
                LayoutType.HORIZONTAL -> LinearParent.parse(LinearDirection.HORIZONTAL, json)
            }

            val child = when(parent?.type) {
                LayoutType.RELATIVE -> RelativeChild.parse(json)
                LayoutType.VERTICAL -> LinearChild.parse(json)
                LayoutType.HORIZONTAL -> LinearChild.parse(json)
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
                LayoutType.parse(json.path("layout")) ?: LayoutType.RELATIVE,
                SelfProps.parse(json),
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

    fun update(inset: Inset = Inset.zero) {
        val params = child.export(view.context, inset)

        self.update(view)

        view.layoutParams = params
        view.setBackgroundColor(background ?: 0)

        for (child in children) {
            child.update(self.padding)
        }
    }

}