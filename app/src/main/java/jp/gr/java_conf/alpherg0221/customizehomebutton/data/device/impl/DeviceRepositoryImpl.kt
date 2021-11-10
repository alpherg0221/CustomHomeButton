package jp.gr.java_conf.alpherg0221.customizehomebutton.data.device.impl

import android.content.Context
import android.provider.Settings
import jp.gr.java_conf.alpherg0221.customizehomebutton.Const
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.device.DeviceRepository

class DeviceRepositoryImpl(
    private val applicationContext: Context
) : DeviceRepository {
    override fun isDefaultAssistApp(): Boolean =
        Settings.Secure.getString(applicationContext.contentResolver, "assistant")
            .contains(Const.appName)
}