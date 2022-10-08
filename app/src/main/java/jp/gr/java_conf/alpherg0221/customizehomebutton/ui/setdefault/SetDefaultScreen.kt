package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setdefault

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import okio.ByteString.Companion.toByteString
import java.nio.charset.Charset


@Composable
fun SetDefaultAssistantScreen(
    reload: () -> Unit
) {
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { reload() }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Set this app as the default Assist app",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { openSettings(startForResult) }) {
                Text(text = "Open Settings")
            }
        }
    }
}

@SuppressLint("WrongConstant")
fun openSettings(startForResult: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val bundle = Bundle()
    val intent = Intent("android.settings.VOICE_INPUT_SETTINGS")

    bundle.putString(":settings:fragment_args_key", "default_assist")
    intent.putExtra(":settings:show_fragment_args", bundle)

    startForResult.launch(intent)
}