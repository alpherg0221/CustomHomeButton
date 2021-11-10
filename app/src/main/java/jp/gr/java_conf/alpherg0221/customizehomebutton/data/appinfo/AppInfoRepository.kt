package jp.gr.java_conf.alpherg0221.customizehomebutton.data.appinfo

import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppInfo
import kotlinx.coroutines.flow.Flow

interface AppInfoRepository {

    suspend fun setSelectedAppInfo(
        appName: String,
        activityName: String,
        packageName: String,
    )

    fun observeSelectedAppInfo(): Flow<AppInfo>

    suspend fun getAppInfoList(): List<AppInfo>

    fun observeIsGrid(): Flow<Boolean>

    suspend fun setIsGrid(isGrid: Boolean)
}