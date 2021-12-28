package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setdefault

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetDefaultAssistantScreen(
    reload: () -> Unit
) {
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { reload() }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Set this app as the default Assist app to use this app",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { openSettings(startForResult) }) {
                Text(text = "Settings")
            }
        }
    }
}

fun openSettings(startForResult: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val intent = Intent().setClassName(
        "com.android.settings",
        "com.android.settings.Settings\$ManageAssistActivity"
    )
    startForResult.launch(intent)
}