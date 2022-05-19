package com.movika.interactives

import android.content.Context
import com.movika.player.sdk.android.defaultplayer.container.ContainerFactory
import com.movika.player.sdk.android.defaultplayer.container.ControlContainer
import com.movika.player.sdk.android.defaultplayer.interactive.ContainerData
import com.movika.player.sdk.base.interactive.EventCallback

class MyContainerFactory(
    private val context: Context
): ContainerFactory {

    override fun create(data: ContainerData, callback: EventCallback): ControlContainer? {

        return when (data.container.type.trim().lowercase()) {
            "randompositionchoice" -> Choice(context, data.container, callback)
            else -> null
        }
    }
}
