package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import jp.gr.java_conf.alpherg0221.customizehomebutton.Const
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.appinfo.AppInfoRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.SettingRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppInfo
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting.SettingViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class HomeUiState(
    val appInfoList: List<AppInfo> = emptyList(),
    val loading: Boolean = false,
)

class HomeViewModel(
    private val appInfoRepository: AppInfoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(loading = true))
    val homeUiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val selectedAppInfo = appInfoRepository.observeSelectedAppInfo().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Const.defaultAppInfo,
    )

    val isGrid = appInfoRepository.observeIsGrid().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        true,
    )

    init {
        refreshAll()
    }

    fun setSelectedAppInfo(appInfo: AppInfo) {
        viewModelScope.launch {
            appInfoRepository.setSelectedAppInfo(
                appName = appInfo.appName,
                activityName = appInfo.activityName,
                packageName = appInfo.packageName,
            )
        }
    }

    fun setIsGrid(isGrid: Boolean) {
        viewModelScope.launch { appInfoRepository.setIsGrid(isGrid) }
    }

    private fun refreshAll() {
        _uiState.update { it.copy(loading = true) }

        viewModelScope.launch {
            val appInfoList = appInfoRepository.getAppInfoList()

            _uiState.update {
                it.copy(
                    appInfoList = appInfoList,
                    loading = false,
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            appInfoRepository: AppInfoRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    appInfoRepository = appInfoRepository
                ) as T
            }
        }
    }
}