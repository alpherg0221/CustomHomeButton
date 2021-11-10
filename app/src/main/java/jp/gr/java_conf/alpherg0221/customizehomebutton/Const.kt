package jp.gr.java_conf.alpherg0221.customizehomebutton

import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppInfo
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.MyAppInfo

class Const {
    companion object {
        val defaultAppInfo = AppInfo(
            icon = null,
            appName = "",
            activityName = "",
            packageName = ""
        )

        val defaultMyAppInfo = MyAppInfo(
            appName = "Error",
            packageName = "Error",
            version = "Error"
        )

        const val appName = "customizehomebutton"
    }
}