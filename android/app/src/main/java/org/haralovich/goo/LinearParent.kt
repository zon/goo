package org.haralovich.goo

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ScaleDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.JsonNode

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

            Log.d("LIN", "SHOW " + view.showDividers)
            Log.d("LIN", "DIV " + view.dividerDrawable + " " + view.dividerDrawable.minimumWidth)

        }
    }

}

enum class LinearDirection {
    HORIZONTAL,
    VERTICAL
}