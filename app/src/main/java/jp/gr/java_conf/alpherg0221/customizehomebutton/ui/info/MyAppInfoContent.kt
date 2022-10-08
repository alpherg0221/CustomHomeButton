package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.PreferencesItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppInfoContent(
    onBack: () -> Unit,
    onVersionClick: () -> Unit,
    onOSSClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_information)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
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