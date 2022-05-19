package com.movika.interactives

import android.content.Context
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.movika.player.sdk.android.defaultplayer.container.ControlContainer
import com.movika.player.sdk.android.defaultplayer.control.ControlView
import com.movika.player.sdk.android.defaultplayer.layout.DefaultControlLayoutFactory
import com.movika.player.sdk.base.interactive.EventCallback
import com.movika.player.sdk.base.interactive.EventInvocation
import com.movika.player.sdk.base.model.Container
import com.movika.player.sdk.base.model.Control
import com.movika.player.sdk.base.model.LayoutParams

class Choice(
    context: Context,
    container: Container,
    private val callback: EventCallback
) : ControlContainer() {

    private val layout = DefaultControlLayoutFactory(context).create(container.layout.type)

    private val controlViewFactory = MyControlViewFactory(context)

    private val view: AppCompatTextView = AppCompatTextView(context)

    private val startTime = container.startTime


    init {
        container.controls.forEach { control ->
            controlViewFactory.create(control)
                .also { controlView ->
                    controlView.registerCallback(container, control)
                    layout.add(controlView, control.layoutParams?.applyOffset())
                }
        }
    }

    override fun getView(): View = layout.getView()

    private fun ControlView.registerCallback(container: Container, control: Control) {
        setEventCallback {
            val eventInvocation = EventInvocation(container, it, control)
            callback.onEvent(eventInvocation)
        }
    }

    private fun LayoutParams.applyOffset(): LayoutParams {

        return copy(
            x = (x ?: 0.0),
            y = (y ?: 0.0)
        )
    }

     override fun onAttach() {
        super.onAttach()
        val timer = object: CountDownTimer(startTime, 1000) {
            override fun onTick(millisUntilFinished: Long){
            }
            override fun onFinish() {
                removeView()
            }
        }
        timer.start()
    }

}