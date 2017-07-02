package org.haralovich.goo

import android.content.Context
import android.view.ViewGroup
import com.fasterxml.jackson.databind.JsonNode

enum class MeasureType {
    DP,
    FILL,
    FIT
}

class Measure(val type: MeasureType = MeasureType.DP, val dp: Float = 0f) {

    companion object {

        val fill = Measure(MeasureType.FILL)
        val fit = Measure(MeasureType.FIT)

        fun parse(json: JsonNode): Measure? {
            return when (json.textOption) {
                "fill" -> fill
                "fit" -> fit
                else -> json.floatOption?.let { Measure(dp = it) }
            }
        }

    }

    fun toValue(context: Context): Int {
        return when(type) {
            MeasureType.DP -> (dp * context.resources.displayMetrics.density).toInt()
            MeasureType.FILL -> ViewGroup.LayoutParams.MATCH_PARENT
            MeasureType.FIT -> ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }

}
