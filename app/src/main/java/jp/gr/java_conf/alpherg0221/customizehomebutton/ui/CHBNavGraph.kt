package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.gr.java_conf.alpherg0221.customizehomebutton.data.AppContainer
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.home.HomeScreen
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.home.HomeViewModel
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.info.MyAppInfoScreen
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setdefault.SetDefaultAssistantScreen
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting.SettingScreen
import jp.gr.java_conf.alpherg0221.customizehomebutton.ui.setting.SettingViewModel

@Composable
fun CHBNavGraph(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    onBack: () -> Unit = {},
    navigationActions: CHBAppNavigationActions,
    startDestination: String = CHBDestinations.HOME_ROUTE,
    reload: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(CHBDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    appInfoRepository = appContainer.appInfoRepository
                )
            )
            HomeScreen(
                homeViewModel = homeViewModel,
                openDrawer = openDrawer,
            )
        }
        composable(CHBDestinations.SETTINGS_ROUTE) {
            val settingsViewModel: SettingViewModel = viewModel(
                factory = SettingViewModel.provideFactory(
                    settingRepository = appContainer.settingRepository
                )
            )
            SettingScreen(
                settingViewModel = settingsViewModel,
                onBack = onBack,
            )
        }
        composable(CHBDestinations.APP_INFO_ROUTE) {
            MyAppInfoScreen(
                navigateToOSS = navigationActions.navigateToOSS,
                onBack = onBack,
            )
        }
        composable(CHBDestinations.DEFAULT_ROUTE) {
            SetDefaultAssistantScreen(reload = reload)
        }
    }
}