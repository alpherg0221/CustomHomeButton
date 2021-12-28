package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.compose.material.BottomSheetLayout
import jp.gr.java_conf.alpherg0221.compose.material.RadioButtonItem
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.utils.toLongString


@Composable
fun SelectThemeSheetContent(
    currentTheme: AppTheme,
    onChecked: (AppTheme) -> Unit,
) {
    BottomSheetLayout(title = stringResource(id = R.string.theme)) {
        AppTheme.values().forEach { appTheme ->
            RadioButtonItem(
                label = appTheme.toLongString(),
                selected = currentTheme == appTheme,
                onChecked = { onChecked(appTheme) },
            )
        }
    }
}