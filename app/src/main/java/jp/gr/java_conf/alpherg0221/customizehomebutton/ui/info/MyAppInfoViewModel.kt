package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import jp.gr.java_conf.alpherg0221.customizehomebutton.Const
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.myappinfo.MyAppInfoRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.setting.SettingRepository
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.MyAppInfo
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting.SettingViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MyAppInfoUiState(
    val myAppInfo: MyAppInfo = Const.defaultMyAppInfo,
    val loading: Boolean = false,
)

class MyAppInfoViewModel(
    private val myAppInfoRepository: MyAppInfoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyAppInfoUiState(loading = true))
    val myAppInfoUiState: StateFlow<MyAppInfoUiState> = _uiState.asStateFlow()

    init {
        refreshAll()
    }

    private fun refreshAll() {
        _uiState.update { it.copy(loading = true) }

        viewModelScope.launch {
            val myAppInfoDeferred = async { myAppInfoRepository.getInfo() }
            val myAppInfo = myAppInfoDeferred.await()

            _uiState.update {
                it.copy(
                    myAppInfo = myAppInfo,
                    loading = false
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            myAppInfoRepository: MyAppInfoRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyAppInfoViewModel(
                    myAppInfoRepository = myAppInfoRepository
                ) as T
            }
        }
    }
}