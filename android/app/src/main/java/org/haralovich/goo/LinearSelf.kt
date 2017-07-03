package org.haralovich.goo

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

class LinearSelf(val direction: LinearDirection, val spacing: Float, padding: Inset) : SelfLayout(padding) {

    companion object {

        fun parse(direction: LinearDirection, json: JsonNode): LinearSelf {
            return LinearSelf(
                direction,
                json.path("spacing").floatValue(),
                Inset.parse(json.path("padding")) ?: Inset.zero
            )
        }

    }

    override fun update(view: View) {
        super.update(view)
        if (view is LinearLayout) {

            view.orientation = when (direction) {
                LinearDirection.VERTICAL -> LinearLayout.VERTICAL
                LinearDirection.HORIZONTAL -> LinearLayout.HORIZONTAL
            }

            if (spacing != 0f) {
                val size = (spacing * view.context.resources.displayMetrics.density).toInt()
                view.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                view.dividerDrawable = BitmapDrawable(
                    view.context.resources,
                    Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
                )

            } else {
                view.showDividers = LinearLayout.SHOW_DIVIDER_NONE
            }

        }
    }

}

enum class LinearDirection {
    HORIZONTAL,
    VERTICAL
}