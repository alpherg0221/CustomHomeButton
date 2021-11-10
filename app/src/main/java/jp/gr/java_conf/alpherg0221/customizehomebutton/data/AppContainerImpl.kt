package jp.gr.java_conf.alpherg0221.customizehomebutton.data

import android.content.Context
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.appinfo.AppInfoRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.appinfo.impl.AppInfoRepositoryImpl
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.device.DeviceRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.device.impl.DeviceRepositoryImpl
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.myappinfo.MyAppInfoRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.myappinfo.impl.MyAppInfoRepositoryImpl
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.SettingRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.impl.SettingRepositoryImpl

interface AppContainer {
    val myAppInfoRepository: MyAppInfoRepository
    val appInfoRepository: AppInfoRepository
    val settingRepository: SettingRepository
    val deviceRepository: DeviceRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val myAppInfoRepository: MyAppInfoRepository by lazy {
        MyAppInfoRepositoryImpl(applicationContext)
    }

    override val appInfoRepository: AppInfoRepository by lazy {
        AppInfoRepositoryImpl(applicationContext)
    }

    override val settingRepository: SettingRepository by lazy {
        SettingRepositoryImpl(applicationContext)
    }

    override val deviceRepository: DeviceRepository by lazy {
        DeviceRepositoryImpl(applicationContext)
    }

}