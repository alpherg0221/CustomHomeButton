package jp.gr.java_conf.alpherg0221.customizehomebutton.data.appinfo.impl

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.appinfo.AppInfoRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class AppInfoRepositoryImpl(
    private val applicationContext: Context
) : AppInfoRepository {

    // database
    private val Context.appInfoDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_info")
    private val selectedAppName = stringPreferencesKey("selectedAppName")
    private val selectedActivityName = stringPreferencesKey("selectedActivityName")
    private val selectedPackageKey = stringPreferencesKey("selectedPackage")
    private val isGridKey = booleanPreferencesKey("isGrid")


    override suspend fun setSelectedAppInfo(
        appName: String,
        activityName: String,
        packageName: String,
    ) {
        withContext(Dispatchers.IO) {
            applicationContext.appInfoDataStore.edit { appInfo ->
                appInfo[selectedAppName] = appName
                appInfo[selectedActivityName] = activityName
                appInfo[selectedPackageKey] = packageName
            }
        }
    }

    override fun observeSelectedAppInfo(): Flow<AppInfo> =
        applicationContext.appInfoDataStore.data.map {
            val pm = applicationContext.packageManager

            try {
                AppInfo(
                    icon = pm.getApplicationIcon(it[selectedPackageKey] ?: "Error").let { icon ->
                        icon.toBitmap(icon.intrinsicWidth * 4, icon.intrinsicHeight * 4)
                            .asImageBitmap()
                    },
                    appName = it[selectedAppName] ?: "Not Selected",
                    activityName = it[selectedActivityName] ?: "Not Selected",
                    packageName = it[selectedPackageKey] ?: "Not Selected"
                )
            } catch (e: PackageManager.NameNotFoundException) {
                AppInfo(
                    icon = null,
                    appName = it[selectedAppName] ?: "Not Selected",
                    activityName = it[selectedActivityName] ?: "Not Selected",
                    packageName = it[selectedPackageKey] ?: "Not Selected"
                )
            }

        }

    override suspend fun getAppInfoList(): List<AppInfo> = withContext(Dispatchers.IO) {
        val appInfoList = mutableListOf<AppInfo>()
        val pm = applicationContext.packageManager
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)

        val resolveInfoList = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        for (info in resolveInfoList) {
            appInfoList.add(
                AppInfo(
                    info.loadIcon(pm).toBitmap().asImageBitmap(),
                    info.loadLabel(pm).toString(),
                    info.activityInfo.name,
                    info.activityInfo.packageName,
                )
            )
        }
        appInfoList.sortedBy { it.appName }
    }

    override fun observeIsGrid(): Flow<Boolean> = applicationContext.appInfoDataStore.data.map {
        it[isGridKey] ?: false
    }

    override suspend fun setIsGrid(isGrid: Boolean) {
        withContext(Dispatchers.IO) {
            applicationContext.appInfoDataStore.edit { appInfo ->
                appInfo[isGridKey] = isGrid
            }
        }
    }
}