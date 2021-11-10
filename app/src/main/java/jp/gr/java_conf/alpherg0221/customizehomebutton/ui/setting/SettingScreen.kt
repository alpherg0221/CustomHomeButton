package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel,
    onBack: () -> Unit
) {
    val theme by settingViewModel.theme.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    SettingContent(
        onBack = onBack,
        onCloseBottomSheet = { scope.launch { sheetState.hide() } },
        backHandlerEnable = sheetState.isVisible,
        sheetState = sheetState,
        theme = theme,
        onClickItem = { scope.launch { sheetState.show() } },
        sheetContent = {
            SelectThemeSheetContent(
                currentTheme = theme,
                onChecked = { theme ->
                    scope.launch { sheetState.hide() }
                    settingViewModel.updateTheme(theme)
                },
            )
        }
    )
}