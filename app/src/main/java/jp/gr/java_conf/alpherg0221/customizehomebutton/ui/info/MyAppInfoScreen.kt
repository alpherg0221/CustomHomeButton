package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun MyAppInfoScreen(
    myAppInfoViewModel: MyAppInfoViewModel,
    navigateToOSS: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by myAppInfoViewModel.myAppInfoUiState.collectAsState()

    MyAppInfoContent(
        onBack = onBack,
        onVersionClick = { },
        onOSSClick = navigateToOSS,
    )
}