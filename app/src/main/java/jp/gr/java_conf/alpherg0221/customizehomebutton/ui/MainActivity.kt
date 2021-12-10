package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import jp.gr.java_conf.alpherg0221.customizehomebutton.CHBApplication
import jp.gr.java_conf.alpherg0221.customizehomebutton.utils.rememberWindowSizeClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val splashScreen = installSplashScreen()

        val appContainer = (application as CHBApplication).container

        setContent {
            val windowSizeClass = rememberWindowSizeClass()
            CHBApp(appContainer, windowSizeClass)
        }
    }
}