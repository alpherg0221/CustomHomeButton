package jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.SettingRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingRepositoryImpl(
    private val applicationContext: Context
) : SettingRepository {

    private val Context.settingDataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")
    private val isDarkThemeKey = booleanPreferencesKey("isDarkTheme")
    private val themeKey = stringPreferencesKey("theme")

    override fun observeDarkTheme(): Flow<Boolean> =
        applicationContext.settingDataStore.data.map { it[isDarkThemeKey] ?: false }

    override fun observeTheme(): Flow<AppTheme> =
        applicationContext.settingDataStore.data.map { settings ->
            settings[themeKey]?.let {
                when (it) {
                    AppTheme.Dark.name -> AppTheme.Dark
                    AppTheme.Light.name -> AppTheme.Light
                    AppTheme.Default.name -> AppTheme.Default
                    else -> AppTheme.Default
                }
            } ?: AppTheme.Default
        }

    override suspend fun setTheme(theme: AppTheme) {
        withContext(Dispatchers.IO) {
            applicationContext.settingDataStore.edit { settings ->
                settings[themeKey] = theme.name
            }
        }
    }

    override suspend fun toggleDarkTheme(toggle: Boolean) {
        withContext(Dispatchers.IO) {
            applicationContext.settingDataStore.edit { setting ->
                setting[isDarkThemeKey] = toggle
            }
        }
    }
}