package jp.gr.java_conf.alpherg0221.customizehomebutton.data.myappinfo.impl

import android.content.Context
import android.content.pm.PackageManager
import jp.gr.java_conf.alpherg0221.customizehomebutton.Const
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.myappinfo.MyAppInfoRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.MyAppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyAppInfoRepositoryImpl(
    private val applicationContext: Context
) : MyAppInfoRepository {

    override suspend fun getInfo(): MyAppInfo {
        val name = applicationContext.packageName
        val pm = applicationContext.packageManager

        return withContext(Dispatchers.IO) {
            try {
                val info = pm.getPackageInfo(name, PackageManager.GET_META_DATA)
                MyAppInfo(
                    info.applicationInfo.loadLabel(pm).toString(),
                    info.packageName,
                    info.versionName,
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                Const.defaultMyAppInfo
            }
        }
    }
}