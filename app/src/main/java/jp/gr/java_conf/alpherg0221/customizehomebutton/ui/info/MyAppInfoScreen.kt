package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun MyAppInfoScreen(
    navigateToOSS: () -> Unit,
    onBack: () -> Unit
) {
    MyAppInfoContent(
        onBack = onBack,
        onVersionClick = { },
        onOSSClick = navigateToOSS,
    )
}