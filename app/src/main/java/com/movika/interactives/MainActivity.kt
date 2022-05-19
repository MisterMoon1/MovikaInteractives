    package com.movika.interactives

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.movika.player.sdk.android.defaultplayer.SimpleInteractivePlayer
import com.movika.player.sdk.android.defaultplayer.container.ContainerFactoryProvider
import com.movika.player.sdk.base.Config
import com.movika.player.sdk.base.asset.ManifestURLAssets
import com.movika.player.sdk.base.model.exception.AssetsLoadException
import com.movika.player.sdk.base.model.exception.ExpiredApiKeyException
import com.movika.player.sdk.base.model.exception.IncompatibleManifestVersionException
import com.movika.player.sdk.base.model.exception.InvalidApiKeyException

    class MainActivity : AppCompatActivity() {

        private var player: SimpleInteractivePlayer? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_cl_states_start)

            player = SimpleInteractivePlayer(this, Config())
                .also { player ->
                    lifecycle.addObserver(player)
                    val factoryItem = ContainerFactoryProvider.FactoryItem(
                        factory = MyContainerFactory(this),
                        priority = 1f
                    )
                    player.addContainerFactory(factoryItem)
                    setContentView(player.view)

                }

            val url = "https://gist.githubusercontent.com/MisterMoon1/603ea65f041af325d39d4c92e23a66db/raw/051c18ebb3a81d1863afe0e88b8eca4087193880/manifest-v3.json"
            player?.run(ManifestURLAssets(url))


            player?.errorObservable?.addObserver { error ->
                val msg = when (error) {
                    is AssetsLoadException -> "Load error!"
                    is InvalidApiKeyException -> "Invalid Api key!"
                    is ExpiredApiKeyException -> "Expired Api key!"
                    is IncompatibleManifestVersionException -> "Incompatible manifest version ${error.receivedVersion}"
                    else -> error::class.simpleName
                }
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                error.printStackTrace()
            }

            val buttonHold = Button(this, simplebutton) {

            }

            buttonHold.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                Intent.FLAG_ACTIVITY_NEW_TASK.also { intent.flags = it }
                //intent.
                //finish()
            }*/


        }

        override fun onDestroy() {
            super.onDestroy()
            player = null
        }


    }