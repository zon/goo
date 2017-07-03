package org.haralovich.goo

import android.view.View
import com.fasterxml.jackson.databind.JsonNode

open class SelfLayout(var padding: Inset = paddingFallback) {

    companion object {

        val paddingFallback = Inset.zero

        fun parse(json: JsonNode): SelfLayout {
            val layout = SelfLayout()
            layout.parse(json)
            return layout
        }

    }

    open fun parse(json: JsonNode) {
        padding = Inset.parse(json.path("padding")) ?: paddingFallback
    }

    open fun update(view: View) {
        val p = padding * view.context.resources.displayMetrics.density
        view.setPadding(p.left.toInt(), p.top.toInt(), p.right.toInt(), p.bottom.toInt())
    }

}
