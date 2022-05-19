package com.movika.interactives

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.movika.player.sdk.android.defaultplayer.control.ControlEventCallback
import com.movika.player.sdk.android.defaultplayer.control.ControlView
import com.movika.player.sdk.base.model.Control
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Button(
    context: Context,
    private val control: Control
) : ControlView {

    var text: String? = null
    lateinit var layoutParams: LinearLayout.LayoutParams
    private val view: AppCompatTextView = AppCompatTextView(context)

    init {
        view.text = control.props
            ?.let { Json.decodeFromString<ButtonProps>(it) }
            ?.text
        view.setBackgroundColor(Color.CYAN)
    }

    override fun getView(): View {
        TODO("Not yet implemented")
    }

    /*override fun getView(): ConstraintLayout {
        return TODO()//ConstraintLayout
    }*/

    override fun setEventCallback(callback: ControlEventCallback) {
        view.setOnClickListener { _ ->
            control.events
                .find { it.type.trim().equals("onClick", ignoreCase = true) }
                ?.let { callback.onEvent(it) }
        }
    }

    fun setBackgroundColor(green: Int) {
        return TODO()
    }
}

private fun createButtonDynamically(mainLayout: Any) {

    return TODO()

    /*val testButton = Button(context = this)
    val also = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).also { testButton.layoutParams = it }
    "Dynamic Button".also { testButton.text = it }
    testButton.setBackgroundColor(Color.GREEN)
    mainLayout.run {
        "Dynamic Button".also { testButton.text = it }
        testButton.setBackgroundColor(Color.GREEN)
        //addView(testButton)
    }*/
}