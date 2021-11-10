package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Brightness4
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.navigationBarsPadding
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.BackHandler
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.InsetAwareTopAppBar
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.PreferencesItem
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.PreferencesTitle

@OptIn(ExperimentalMaterialApi::class)
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
        sheetContent = sheetContent,
    ) {
        Scaffold(
            topBar = {
                InsetAwareTopAppBar(
                    title = { Text(text = stringResource(R.string.settings)) },
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
            content = {
                Column(modifier = Modifier.navigationBarsPadding()) {
                    PreferencesTitle(title = stringResource(id = R.string.general))
                    PreferencesItem(
                        title = stringResource(R.string.theme),
                        subtitle = theme.name,
                        onClick = onClickItem,
                        icon = Icons.Rounded.Brightness4,
                    )
                    Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
                }
            }
        )
    }
}