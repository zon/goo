package org.haralovich.goo

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fasterxml.jackson.databind.JsonNode

enum class ViewType(val key: String) {
    RELATIVE("relative"),
    VERTICAL("vertical"),
    HORIZONTAL("horizontal"),
    LABEL("label"),
    BUTTON("button"),
    IMAGE("image");

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
            BUTTON -> Button(context)
            IMAGE -> ImageView(context)
        }
    }

    fun exportProps(json: JsonNode): ViewProps {
        return when (this) {
            RELATIVE -> ViewProps(json)
            VERTICAL -> LinearProps(LinearDirection.VERTICAL, json)
            HORIZONTAL -> LinearProps(LinearDirection.HORIZONTAL, json)
            LABEL -> LabelProps(json)
            BUTTON -> LabelProps(json)
            IMAGE -> ImageProps(json)
        }
    }

}
