package org.haralovich.goo

import android.view.View
import android.widget.RelativeLayout

var RelativeLayout.LayoutParams.alignParentLeft: Boolean
    get() {
        return getRule(RelativeLayout.ALIGN_PARENT_LEFT) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.ALIGN_PARENT_LEFT, if (value) RelativeLayout.TRUE else 0)
    }

var RelativeLayout.LayoutParams.alignParentRight: Boolean
    get() {
        return getRule(RelativeLayout.ALIGN_PARENT_RIGHT) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.ALIGN_PARENT_RIGHT, if (value) RelativeLayout.TRUE else 0)
    }

var RelativeLayout.LayoutParams.alignParentTop: Boolean
    get() {
        return getRule(RelativeLayout.ALIGN_PARENT_TOP) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.ALIGN_PARENT_TOP, if (value) RelativeLayout.TRUE else 0)
    }

var RelativeLayout.LayoutParams.alignParentBottom: Boolean
    get() {
        return getRule(RelativeLayout.ALIGN_PARENT_BOTTOM) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, if (value) RelativeLayout.TRUE else 0)
    }

var RelativeLayout.LayoutParams.leftOf: Int
    get() {
        return getRule(RelativeLayout.LEFT_OF)
    }
    set(value) {
        addRule(RelativeLayout.LEFT_OF, value)
    }

var RelativeLayout.LayoutParams.rightOf: Int
    get() {
        return getRule(RelativeLayout.RIGHT_OF)
    }
    set(value) {
        addRule(RelativeLayout.RIGHT_OF, value)
    }

var RelativeLayout.LayoutParams.below: Int
    get() {
        return getRule(RelativeLayout.BELOW)
    }
    set(value) {
        addRule(RelativeLayout.BELOW, value)
    }

var RelativeLayout.LayoutParams.above: Int
    get() {
        return getRule(RelativeLayout.ABOVE)
    }
    set(value) {
        addRule(RelativeLayout.ABOVE, value)
    }

var RelativeLayout.LayoutParams.centerHorizontal: Boolean
    get() {
        return getRule(RelativeLayout.CENTER_HORIZONTAL) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.CENTER_HORIZONTAL, if (value) RelativeLayout.TRUE else 0)
    }

var RelativeLayout.LayoutParams.centerInParent: Boolean
    get() {
        return getRule(RelativeLayout.CENTER_IN_PARENT) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.CENTER_IN_PARENT, if (value) RelativeLayout.TRUE else 0)
    }

var RelativeLayout.LayoutParams.centerVertical: Boolean
    get() {
        return getRule(RelativeLayout.CENTER_VERTICAL) == RelativeLayout.TRUE
    }
    set(value) {
        addRule(RelativeLayout.CENTER_VERTICAL, if (value) RelativeLayout.TRUE else 0)
    }