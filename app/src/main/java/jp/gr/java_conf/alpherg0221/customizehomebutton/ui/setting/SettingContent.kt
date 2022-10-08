package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Brightness4
import androidx.compose.material.icons.rounded.Brightness7
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.PreferencesItem
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.PreferencesTitle
import jp.gr.java_conf.alpherg0221.customizehomebutton.utils.toLongString

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(
    onBack: () -> Unit,
    onCloseBottomSheet: () -> Unit = {},
    backHandlerEnable: Boolean,
    sheetState: ModalBottomSheetState,
    theme: AppTheme,
    onClickItem: () -> Unit = {},
    sheetContent: @Composable ColumnScope.() -> Unit
) {
    BackHandler(enabled = backHandlerEnable, onBack = onCloseBottomSheet)
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = MaterialTheme.shapes.extraLarge.copy(
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp),
        ),
        sheetContent = sheetContent,
        sheetBackgroundColor = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text(text = stringResource(R.string.settings)) },
                    modifier = Modifier.statusBarsPadding(),
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                PreferencesTitle(title = stringResource(id = R.string.general))
                PreferencesItem(
                    title = stringResource(R.string.theme),
                    subtitle = theme.toLongString(),
                    onClick = onClickItem,
                    icon = when (theme) {
                        AppTheme.Dark -> Icons.Rounded.Brightness4
                        AppTheme.Light -> Icons.Rounded.Brightness7
                        AppTheme.Default ->
                            when (LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                                Configuration.UI_MODE_NIGHT_YES -> Icons.Rounded.Brightness4
                                Configuration.UI_MODE_NIGHT_NO -> Icons.Rounded.Brightness7
                                else -> Icons.Rounded.Error
                            }
                    },
                )
                Divider()
            }
        }
    }
}