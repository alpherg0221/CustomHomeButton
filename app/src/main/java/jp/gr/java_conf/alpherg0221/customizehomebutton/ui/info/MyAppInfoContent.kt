package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.navigationBarsPadding
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.MyAppInfo
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.InsetAwareTopAppBar
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.PreferencesItem

@Composable
fun MyAppInfoContent(
    onBack: () -> Unit,
    onVersionClick: () -> Unit,
    onOSSClick: () -> Unit
) {
    Scaffold(
        topBar = {
            InsetAwareTopAppBar(
                title = { Text(text = stringResource(R.string.app_information)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            PreferencesItem(
                title = stringResource(R.string.version),
                subtitle = stringResource(id = R.string.version_sub),
                onClick = onVersionClick
            )
            PreferencesItem(
                title = stringResource(id = R.string.oss),
                subtitle = stringResource(id = R.string.oss_sub),
                onClick = onOSSClick
            )
        }
    }
}