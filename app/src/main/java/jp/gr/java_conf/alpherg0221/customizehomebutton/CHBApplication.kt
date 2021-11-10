package jp.gr.java_conf.alpherg0221.customizehomebutton

import android.app.Application
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.AppContainer
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.AppContainerImpl

class CHBApplication : Application() {

    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}