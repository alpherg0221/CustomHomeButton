package jp.gr.java_conf.alpherg0221.customizehomebutton.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarViewMonth
import androidx.compose.material.icons.rounded.FormatListBulleted
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit,
) {
    val uiState by homeViewModel.homeUiState.collectAsState()
    val selectedAppInfo by homeViewModel.selectedAppInfo.collectAsState()
    val isGrid by homeViewModel.isGrid.collectAsState()

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    HomeContent(
        scaffoldState = scaffoldState,
        listState = listState,
        isLoading = uiState.loading,
        isGrid = isGrid,
        orientation = context.resources.configuration.orientation,
        selectedAppInfo = selectedAppInfo,
        appInfoList = uiState.appInfoList,
        openDrawer = openDrawer,
        onSelectedClick = {
            scope.launch {
                val intent = Intent()
                    .setAction("android.intent.category.LAUNCHER")
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .setClassName(selectedAppInfo.packageName, selectedAppInfo.activityName)
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Failed to launch the app",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        },
        onListClick = { scope.launch { homeViewModel.setSelectedAppInfo(it) } },
        actions = {
            IconButton(onClick = { scope.launch { homeViewModel.setIsGrid(!isGrid) } }) {
                Icon(
                    imageVector = if (isGrid) Icons.Rounded.FormatListBulleted else Icons.Rounded.CalendarViewMonth,
                    contentDescription = null
                )
            }
        }
    )
}