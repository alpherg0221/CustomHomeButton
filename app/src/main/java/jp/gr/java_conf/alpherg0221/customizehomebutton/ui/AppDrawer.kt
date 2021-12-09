package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.DrawerButton

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToInfo: () -> Unit,
    closeDrawer: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(24.dp))
        CHBLogo(Modifier.padding(20.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Rounded.Home,
            label = stringResource(R.string.home),
            isSelected = currentRoute == CHBDestinations.HOME_ROUTE,
            action = {
                navigateToHome()
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Rounded.Settings,
            label = stringResource(R.string.settings),
            isSelected = currentRoute == CHBDestinations.SETTINGS_ROUTE,
            action = {
                navigateToSetting()
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Rounded.Info,
            label = stringResource(R.string.app_information),
            isSelected = currentRoute == CHBDestinations.APP_INFO_ROUTE,
            action = {
                navigateToInfo()
                closeDrawer()
            }
        )
    }
}

@Composable
fun CHBLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier.background(shape = MaterialTheme.shapes.small, color = Color.White),
            imageVector = Icons.Filled.OpenInNew,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color(0xFF05A2E2))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace
        )
    }
}