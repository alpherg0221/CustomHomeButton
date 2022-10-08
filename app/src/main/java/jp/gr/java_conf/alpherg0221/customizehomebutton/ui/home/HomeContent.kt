package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
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
import customizehomebutton.R
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppInfo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    snackbarHostState: SnackbarHostState,
    listState: LazyListState,
    isLoading: Boolean,
    isGrid: Boolean,
    orientation: Int,
    selectedAppInfo: AppInfo,
    appInfoList: List<AppInfo>,
    openDrawer: () -> Unit,
    onSelectedClick: () -> Unit,
    onListClick: (AppInfo) -> Unit,
    actionClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "CustomizeHomeButton") },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = actionClick) {
                        Icon(
                            imageVector = if (isGrid) {
                                Icons.Rounded.FormatListBulleted
                            } else {
                                Icons.Rounded.CalendarViewMonth
                            },
                            contentDescription = null
                        )
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Title()
            SelectedItem(onClick = onSelectedClick, appInfo = selectedAppInfo)
            Divider()
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    if (isGrid) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            items(appInfoList.size) { index ->
                                GridItem(appInfo = appInfoList[index], onClick = onListClick)
                            }
                        }
                    } else {
                        LazyColumn(state = listState) {
                            items(appInfoList.size) { index ->
                                ListItem(appInfo = appInfoList[index], onClick = onListClick)
                                Divider(
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),
                                )
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
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.selected_app),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SelectedItem(appInfo: AppInfo, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {
        ListItemContent(appInfo)
    }
}

@Composable
fun ListItem(appInfo: AppInfo, onClick: (AppInfo) -> Unit) {
    Surface(
        modifier = Modifier
            .clickable { onClick(appInfo) }
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {
        ListItemContent(appInfo)
    }
}

@Composable
fun ListItemContent(appInfo: AppInfo) {
    val (icon, appName, _, packageName) = appInfo

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            bitmap = icon ?: ImageBitmap(50, 50),
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit,
            colorFilter = if (icon == null) ColorFilter.tint(MaterialTheme.colorScheme.error) else null
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = appName,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
            )
            Text(
                text = packageName,
                color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun GridItem(appInfo: AppInfo, onClick: (AppInfo) -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .clickable { onClick(appInfo) }
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
            bitmap = icon ?: ImageBitmap(60, 60),
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .size(60.dp),
            contentScale = ContentScale.Fit,
            colorFilter = if (icon == null) ColorFilter.tint(MaterialTheme.colorScheme.error) else null
        )
        Text(
            text = appName,
            modifier = Modifier.padding(5.dp),
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}