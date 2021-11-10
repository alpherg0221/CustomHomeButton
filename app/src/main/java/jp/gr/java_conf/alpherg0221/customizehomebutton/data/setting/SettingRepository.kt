package jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting

import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun observeDarkTheme(): Flow<Boolean>

    fun observeTheme(): Flow<AppTheme>

    suspend fun setTheme(theme: AppTheme)

    suspend fun toggleDarkTheme(toggle: Boolean)
}