package jp.gr.java_conf.alpherg0221.customizehomebutton.model

import androidx.compose.ui.graphics.ImageBitmap

data class AppInfo(
    val icon: ImageBitmap?,
    val appName: String,
    val activityName: String,
    val packageName: String,
)

data class MyAppInfo(
    val appName: String,
    val packageName: String,
    val version: String,
)

enum class AppTheme {
    Dark,
    Light,
    Default,
}