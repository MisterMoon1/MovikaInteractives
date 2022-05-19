package com.movika.interactives

import android.app.Application
import com.movika.player.sdk.InitConfig
import com.movika.player.sdk.android.MovikaSdk

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupMovikaSdk()
    }

    private fun setupMovikaSdk() {
        val initConfig = InitConfig(
            apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBOYW1lIjoiY29tLm1vdmlrYS5hbmRyb2lkLmV4YW1wbGUiLCJhcHBsaWNhdGlvbl9saW5rIjoiIiwiaWQiOjM4OSwicGxhdGZvcm0iOjJ9.J9oBevGpJZGPkqgDboR5LTbUWymF0bmGxmB_DXdH-dQ",
            appName = "com.movika.android.example",
            appVersion = "1.6.1",
        )
        MovikaSdk.setup(initConfig, this)
    }
}
