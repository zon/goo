package org.haralovich.goo

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.fasterxml.jackson.databind.JsonNode

class Element(
    context: Context,
    val id: String?,
    val transform: Transform,
    val layout: Layout,
    val background: Int?,
    var children: Set<Element>
) {
    val view: View = RelativeLayout(context)

    init {
        if (view is ViewGroup) {
            for (child in children) {
                view.addView(child.view)
            }
        }
    }

    companion object {

        val TAG = "Element"

        fun parse(context: Context, json: JsonNode, transform: Transform? = null): Element {
            return Element(
                context = context,
                id = json.path("id").textOption,
                transform = transform ?: Transform.parse(json),
                layout = Layout.parse(json),
                background = json.path("background").colorOption,
                children = json.path("children").map { Element.parse(context, it) }.toSet()
            )
        }

        fun root(context: Context, json: JsonNode): Element {
            val metrics = context.resources.displayMetrics
            val transform = Transform(
                anchor = Anchor.fill,
                width = metrics.widthPixels / metrics.density,
                height = metrics.heightPixels / metrics.density
            )
            return parse(context, json, transform)
        }

    }

    fun update(within: Rect) {

        val frame = transform.export(within)
        view.layout(
            frame.left.toInt(),
            frame.top.toInt(),
            frame.right.toInt(),
            frame.bottom.toInt()
        )

        Log.d(TAG, "FRAME "+ frame)

        val padded = Rect(Vector.zero, (within + frame).size) + layout.padding
        if (layout.type != LayoutType.RELATIVE) {
            val included = children.filter { !it.layout.ignore }

            var delta = Vector.zero
            var weight = 0f
            if (layout.distribute != Vector.zero) {
                val size = Vector(
                    included.map { it.transform.width }.reduce { a, b -> a + b },
                    included.map { it.transform.height }.reduce { a, b -> a + b }
                )
                val spacing = layout.spacing * maxOf(included.count() - 1, 0)
                delta = padded.size - spacing - size
                weight = included.map { it.layout.weight }.reduce { a, b -> a + b }
            }

            val position = Vector.zero
            for (child in included) {
                val cell = Rect(padded.origin + position, padded.size)

                if (layout.distribute.x != 0f && layout.type == LayoutType.HORIZONTAL) {
                    cell.size.x = child.transform.width + delta.x * (child.layout.weight / weight)

                } else if (layout.distribute.y != 0f && layout.type == LayoutType.VERTICAL) {
                    cell.size.y = child.transform.height + delta.y * (child.layout.weight / weight)
                }

                child.update(cell)

                if (layout.type == LayoutType.HORIZONTAL) {
                    position.x = cell.origin.x + layout.spacing.x - layout.padding.left

                } else if (layout.type == LayoutType.VERTICAL) {
                    position.y = cell.origin.y + layout.spacing.y - layout.padding.top
                }
            }

            for (child in children.filter { it.layout.ignore }) {
                child.update(padded)
            }

        } else {
            for (child in children) {
                child.update(padded)
            }
        }

//        view.setBackgroundColor(background ?: 0)

        view.setBackgroundColor(Color.MAGENTA)

        Log.d(TAG, "BACKGROUND "+ background)

    }

    fun update() {
        update(Rect(Vector.zero, Vector(transform.width, transform.height)))
    }

}