package org.haralovich.goo

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.fasterxml.jackson.databind.JsonNode

enum class ViewType(val key: String) {
    RELATIVE("relative"),
    VERTICAL("vertical"),
    HORIZONTAL("horizontal"),
    LABEL("label");

    companion object {

        fun parse(json: JsonNode): ViewType? {
            val key = json.textOption
            if (key != null) {
                return enumValues<ViewType>().find { it.key == key }
            } else {
                return null
            }
        }

    }

    fun export(context: Context): View {
        return when (this) {
            RELATIVE -> RelativeLayout(context)
            VERTICAL -> LinearLayout(context)
            HORIZONTAL -> LinearLayout(context)
            LABEL -> TextView(context)
        }
    }

}
