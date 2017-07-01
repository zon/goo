package org.haralovich.goo

import android.content.Context
import android.view.ViewGroup

abstract class ChildProps<out P: ViewGroup.LayoutParams>(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float,
    val width: Float?,
    val height: Float?
) {

    abstract fun export(context: Context, inset: Inset): P

}