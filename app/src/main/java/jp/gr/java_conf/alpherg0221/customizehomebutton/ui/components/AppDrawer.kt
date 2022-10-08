package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.CHBDest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer3(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToInfo: () -> Unit,
    closeDrawer: () -> Unit,
) {
    ModalDrawerSheet {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        CHBLogo()
        DrawerItem(
            label = stringResource(R.string.home),
            selected = currentRoute == CHBDest.HOME_ROUTE,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            icon = Icons.Rounded.Home,
        )
        DrawerItem(
            label = stringResource(R.string.settings),
            selected = currentRoute == CHBDest.SETTINGS_ROUTE,
            onClick = {
                navigateToSetting()
                closeDrawer()
            },
            icon = Icons.Rounded.Settings,
        )
        DrawerItem(
            label = stringResource(R.string.app_information),
            selected = currentRoute == CHBDest.APP_INFO_ROUTE,
            onClick = {
                navigateToInfo()
                closeDrawer()
            },
            icon = Icons.Rounded.Info,
        )
    }
}

@Composable
fun CHBLogo() {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .background(shape = CircleShape, color = Color.White)
                .padding(4.dp),
            tint = Color(0xFF05A2E2),
            imageVector = Icons.Filled.OpenInNew,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(
    label: String = "",
    selected: Boolean = false,
    onClick: () -> Unit = {},
    icon: ImageVector = Icons.Rounded.Error,
) {
    NavigationDrawerItem(
        label = { Text(text = label) },
        selected = selected,
        onClick = onClick,
        icon = { Icon(imageVector = icon, contentDescription = null) },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
    )
}