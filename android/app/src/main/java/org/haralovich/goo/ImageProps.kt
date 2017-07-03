package org.haralovich.goo

import android.view.View
import android.widget.ImageView
import com.fasterxml.jackson.databind.JsonNode

class ImageProps(json: JsonNode) : ViewProps(json) {
    val src = json.path("src").textOption

    override fun update(view: View) {
        super.update(view)
        if (view is ImageView) {

            val drawable = src
                ?.let { view.resources.getIdentifier(it, "drawable", view.context.packageName) }
                ?.let { if (it != 0) view.resources.getDrawable(it, view.context.theme) else null }

            view.setImageDrawable(drawable)
        }
    }

}