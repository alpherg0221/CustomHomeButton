package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import customizehomebutton.R

@Composable
fun MyAppInfoScreen(
    navigateToOSS: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val version = stringResource(id = R.string.version_sub)

    MyAppInfoContent(
        onBack = onBack,
        onVersionClick = {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(
                ClipData.newPlainText(
                    "CustomizeHomeButton Version",
                    "CustomizeHomeButton Version $version",
                ),
            )
            Toast.makeText(context, "copied", Toast.LENGTH_SHORT).show()
        },
        onOSSClick = navigateToOSS,
    )
}