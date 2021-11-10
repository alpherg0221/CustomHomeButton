package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun MyAppInfoScreen(
    myAppInfoViewModel: MyAppInfoViewModel,
    onBack: () -> Unit
) {
    val uiState by myAppInfoViewModel.myAppInfoUiState.collectAsState()

    val context = LocalContext.current

    MyAppInfoContent(
        onBack = onBack,
        info = uiState.myAppInfo,
        onVersionClick = { },
        onOSSClick = {
            context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
    )
}