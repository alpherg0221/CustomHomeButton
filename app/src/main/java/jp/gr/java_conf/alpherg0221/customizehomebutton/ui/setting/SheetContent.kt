package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.BottomSheetLayout
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

@Composable
fun CheckboxItem(
    label: String,
    checked: Boolean,
    onChecked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChecked() }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onChecked() },
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 18.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RadioButtonItem(
    label: String,
    selected: Boolean,
    onChecked: () -> Unit = {},
) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onChecked() }
                .padding(horizontal = 24.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = onChecked,
            )
            Text(
                text = label,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }
}