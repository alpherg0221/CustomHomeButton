package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MyAppInfoUiState(
    val loading: Boolean = false,
)

class MyAppInfoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyAppInfoUiState(loading = true))
    val myAppInfoUiState: StateFlow<MyAppInfoUiState> = _uiState.asStateFlow()

    init {
        refreshAll()
    }

    private fun refreshAll() {
        _uiState.update { it.copy(loading = true) }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyAppInfoViewModel() as T
            }
        }
    }
}