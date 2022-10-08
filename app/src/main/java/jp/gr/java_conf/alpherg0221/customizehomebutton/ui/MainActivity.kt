package jp.gr.java_conf.alpherg0221.customizehomebutton.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import jp.gr.java_conf.alpherg0221.customizehomebutton.CHBApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        val appContainer = (application as CHBApplication).container

        setContent {
            CHBApp(appContainer)
        }
    }
}