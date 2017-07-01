package org.haralovich.goo

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = Goo.load(applicationContext, "test")
        val element = Element.root(applicationContext, json)
        element.update()

        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar)
        setContentView(element.view)
    }
}
