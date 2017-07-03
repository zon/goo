package org.haralovich.goo

import android.view.View
import com.fasterxml.jackson.databind.JsonNode

open class ViewProps(json: JsonNode) {
    var padding = Inset.parse(json.path("padding")) ?: Inset.zero

    open fun update(view: View) {
        val p = padding * view.context.resources.displayMetrics.density
        view.setPadding(p.left.toInt(), p.top.toInt(), p.right.toInt(), p.bottom.toInt())
    }

}
