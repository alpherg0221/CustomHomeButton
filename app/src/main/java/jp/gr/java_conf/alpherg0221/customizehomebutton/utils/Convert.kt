package jp.gr.java_conf.alpherg0221.customizehomebutton.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme


@Composable
fun AppTheme.toLongString(): String {
    return when (this) {
        AppTheme.Dark -> stringResource(id = R.string.dark_theme)
        AppTheme.Light -> stringResource(id = R.string.light_theme)
        AppTheme.Default -> stringResource(id = R.string.default_theme)
    }
}
