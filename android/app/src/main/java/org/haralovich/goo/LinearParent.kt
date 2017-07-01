package org.haralovich.goo

import android.view.View
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

enum class LinearDirection {
    HORIZONTAL,
    VERTICAL
}

class LinearParent(val direction: LinearDirection, val spacing: Float, padding: Inset) : SelfProps(padding) {

    companion object {

        fun parse(direction: LinearDirection, json: JsonNode): LinearParent {
            return LinearParent(
                direction,
                json.path("spacing").floatValue(),
                Inset.parse(json.path("padding")) ?: Inset.zero
            )
        }

    }

    override fun update(view: View) {
        if (view is LinearLayout) {
            view.orientation = when (direction) {
                LinearDirection.VERTICAL -> LinearLayout.VERTICAL
                LinearDirection.HORIZONTAL -> LinearLayout.HORIZONTAL
            }
        }
    }

}
