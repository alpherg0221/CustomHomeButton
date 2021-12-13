package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.compose.material.InsetAwareTopAppBar
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppInfo


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    scaffoldState: ScaffoldState,
    listState: LazyListState,
    isLoading: Boolean,
    isGrid: Boolean,
    orientation: Int,
    selectedAppInfo: AppInfo,
    appInfoList: List<AppInfo>,
    openDrawer: () -> Unit,
    onSelectedClick: () -> Unit,
    onListClick: (AppInfo) -> Unit,
    actions: @Composable RowScope.() -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            InsetAwareTopAppBar(
                title = { Text(text = "CustomizeHomeButton") },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = actions,
            )
        },
    ) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            Surface(elevation = 4.dp) {
                Column {
                    Title()
                    SelectedItem(
                        onClick = onSelectedClick,
                        appInfo = selectedAppInfo
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    if (isGrid) {
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3),
                            contentPadding = PaddingValues(3.dp)
                        ) {
                            items(appInfoList.size) { index ->
                                GridItem(
                                    appInfo = appInfoList[index],
                                    onClick = onListClick
                                )
                            }
                        }
                    } else {
                        LazyColumn(state = listState) {
                            items(appInfoList.size) { index ->
                                ListItem(
                                    appInfo = appInfoList[index],
                                    onClick = onListClick
                                )
                                Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Title() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
            .fillMaxWidth()
    ) {
        val color = MaterialTheme.colors.primary
        Image(
            imageVector = Icons.Rounded.Check,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color),
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.selected_app),
            style = MaterialTheme.typography.body2,
            color = color
        )
    }
}

@Composable
fun SelectedItem(
    appInfo: AppInfo,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        ListItemContent(appInfo)
    }
}

@Composable
fun ListItem(
    appInfo: AppInfo,
    onClick: (AppInfo) -> Unit
) {
    TextButton(
        onClick = { onClick(appInfo) },
        modifier = Modifier.fillMaxWidth()
    ) {
        ListItemContent(appInfo)
    }
}

@Composable
fun ListItemContent(appInfo: AppInfo) {
    val (icon, appName, _, packageName) = appInfo

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            bitmap = icon ?: ImageBitmap(50, 50),
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit,
            colorFilter = if (icon == null) ColorFilter.tint(MaterialTheme.colors.error) else null
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = appName,
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = packageName,
                color = MaterialTheme.colors.onSurface.copy(0.6f),
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun GridItem(
    appInfo: AppInfo,
    onClick: (AppInfo) -> Unit
) {
    Card(
        onClick = { onClick(appInfo) },
        modifier = Modifier
            .padding(3.dp)
            .fillMaxSize()
    ) {
        GridItemContent(appInfo)
    }
}

@Composable
fun GridItemContent(appInfo: AppInfo) {
    val (icon, appName, _, _) = appInfo

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = icon ?: ImageBitmap(50, 50),
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp),
            contentScale = ContentScale.Fit,
            colorFilter = if (icon == null) ColorFilter.tint(MaterialTheme.colors.error) else null
        )
        Text(
            text = appName,
            modifier = Modifier.padding(5.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.caption,
        )
    }
}