package com.movika.interactives

import android.content.Context
import com.movika.player.sdk.android.defaultplayer.control.ControlView
import com.movika.player.sdk.android.defaultplayer.control.ControlViewFactory
import com.movika.player.sdk.base.model.Control

class MyControlViewFactory(private val context: Context): ControlViewFactory {

    override fun create(control: Control): ControlView {
        return when (control.type.trim().lowercase()) {
            "simplebutton" -> Button(context, control)
            else -> throw IllegalArgumentException("Unsupported control type ${control.type}")
        }
    }


}