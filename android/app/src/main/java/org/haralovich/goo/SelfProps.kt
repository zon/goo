package org.haralovich.goo

import android.view.View
import com.fasterxml.jackson.databind.JsonNode

open class SelfProps(val padding: Inset) {

    companion object {

        fun parse(json: JsonNode): SelfProps {
            return SelfProps(Inset.parse(json.path("padding")) ?: Inset.zero)
        }

    }

    open fun update(view: View) {
        val p = padding * view.context.resources.displayMetrics.density
        view.setPadding(p.left.toInt(), p.top.toInt(), p.right.toInt(), p.bottom.toInt())
    }

}
