package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jp.gr.java_conf.alpherg0221.compose.material.theme.BlueJadeTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.AppContainer
import jp.gr.java_conf.alpherg0221.customizehomebutton.model.AppTheme
import jp.gr.java_conf.alpherg0221.customizehomebutton.utils.WindowSize
import kotlinx.coroutines.launch

@Composable
fun CHBApp(
    appContainer: AppContainer,
    windowSize: WindowSize,
) {
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
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = darkIcons)
        }

        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            CHBAppNavigationActions(navController)
        }

        val scope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: CHBDestinations.HOME_ROUTE

        val isExpandedScreen = windowSize == WindowSize.Expanded
        val sizeAwareDrawerState = rememberDrawerState(DrawerValue.Closed)

        BackHandler(
            enabled = sizeAwareDrawerState.isOpen,
            onBack = { scope.launch { sizeAwareDrawerState.close() } }
        )

        ModalDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToSetting = navigationActions.navigateToSetting,
                    navigateToInfo = navigationActions.navigateToInfo,
                    closeDrawer = { scope.launch { sizeAwareDrawerState.close() } }
                )
            },
            modifier = Modifier.systemBarsPadding().navigationBarsPadding(),
            drawerState = sizeAwareDrawerState,
            gesturesEnabled = currentRoute == CHBDestinations.HOME_ROUTE,
        ) {
            CHBNavGraph(
                appContainer = appContainer,
                navController = navController,
                openDrawer = { scope.launch { sizeAwareDrawerState.open() } },
                onBack = navigationActions.onBack,
                navigationActions = navigationActions,
                startDestination = if (isDefaultAssistApp.value) CHBDestinations.HOME_ROUTE else CHBDestinations.DEFAULT_ROUTE,
                reload = {
                    isDefaultAssistApp.value =
                        appContainer.deviceRepository.isDefaultAssistApp()
                }
            )
        }
    }
}