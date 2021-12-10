package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import android.content.Intent
import androidx.navigation.NavHostController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

object CHBDestinations {
    const val HOME_ROUTE = "home"
    const val SETTINGS_ROUTE = "settings"
    const val APP_INFO_ROUTE = "app_info"
    const val DEFAULT_ROUTE = "default"
}

class CHBAppNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(CHBDestinations.HOME_ROUTE)
    }
    val navigateToSetting: () -> Unit = {
        navController.navigate(CHBDestinations.SETTINGS_ROUTE)
    }
    val navigateToInfo: () -> Unit = {
        navController.navigate(CHBDestinations.APP_INFO_ROUTE)
    }
    val navigateToOSS: () -> Unit = {
        val context = navController.context
        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
    }
    val onBack: () -> Unit = {
        navController.navigateUp()
    }
}
