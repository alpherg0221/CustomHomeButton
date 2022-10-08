package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jp.gr.java_conf.alpherg0221.compose.material3.theme.BlueJadeTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.AppContainer
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.components.AppDrawer3
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CHBApp(appContainer: AppContainer) {
    val theme by appContainer.settingRepository.observeTheme()
        .collectAsState(initial = AppTheme.Default)
    val isDefaultAssistApp = remember {
        mutableStateOf(appContainer.deviceRepository.isDefaultAssistApp())
    }

    BlueJadeTheme(
        darkTheme = when (theme) {
            AppTheme.Dark -> true
            AppTheme.Light -> false
            AppTheme.Default -> isSystemInDarkTheme()
        }
    ) {
        val systemUiController = rememberSystemUiController()
        val darkIcons = !isSystemInDarkTheme()
        SideEffect {
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = darkIcons)
        }

        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            CHBAppNavigationActions(navController)
        }

        val scope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: CHBDest.HOME_ROUTE

        val drawerState = rememberDrawerState(DrawerValue.Closed)

        BackHandler(
            enabled = drawerState.isOpen,
            onBack = { scope.launch { drawerState.close() } }
        )

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer3(
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToSetting = navigationActions.navigateToSetting,
                    navigateToInfo = navigationActions.navigateToInfo,
                    closeDrawer = { scope.launch { drawerState.close() } }
                )
            },
            drawerState = drawerState,
            gesturesEnabled = currentRoute == CHBDest.HOME_ROUTE,
        ) {
            CHBNavGraph(
                appContainer = appContainer,
                navController = navController,
                openDrawer = { scope.launch { drawerState.open() } },
                onBack = navigationActions.onBack,
                navigationActions = navigationActions,
                startDestination = if (isDefaultAssistApp.value) CHBDest.HOME_ROUTE else CHBDest.DEFAULT_ROUTE,
                reload = {
                    isDefaultAssistApp.value =
                        appContainer.deviceRepository.isDefaultAssistApp()
                }
            )
        }
    }
}