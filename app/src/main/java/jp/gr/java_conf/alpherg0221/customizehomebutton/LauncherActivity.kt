package jp.gr.java_conf.alpherg0221.customizehomebutton

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as CHBApplication).container
        val appInfoRepository = appContainer.appInfoRepository

        lifecycleScope.launch {
            val (_, _, activityName, packageName) =
                appInfoRepository.observeSelectedAppInfo().first()

            if (activityName == "Not Selected" || packageName == "Not Selected") {

            }

            startTargetActivity(
                activityName = activityName,
                packageName = packageName,
            )
        }
    }

    private fun startTargetActivity(
        activityName: String,
        packageName: String,
    ) {
        val intent = Intent()

        intent.action = Intent.CATEGORY_LAUNCHER
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setClassName(packageName, activityName)

        startActivity(intent)
        finishAndRemoveTask()
    }
}