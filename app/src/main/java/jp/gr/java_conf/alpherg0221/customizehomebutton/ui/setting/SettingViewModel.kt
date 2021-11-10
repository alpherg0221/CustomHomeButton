package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.SettingRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    val theme = settingRepository.observeTheme().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        AppTheme.Dark,
    )

    fun updateTheme(theme: AppTheme) {
        viewModelScope.launch {
            settingRepository.setTheme(theme)
        }
    }

    companion object {
        fun provideFactory(
            settingRepository: SettingRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingViewModel(
                    settingRepository = settingRepository
                ) as T
            }
        }
    }
}