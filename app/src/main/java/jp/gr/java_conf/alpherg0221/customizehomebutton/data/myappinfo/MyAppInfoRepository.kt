package jp.gr.java_conf.alpherg0221.customizehomebutton.data.myappinfo

import jp.gr.java_conf.alpherg0221.customizehomebutton.model.MyAppInfo

interface MyAppInfoRepository {
    suspend fun getInfo(): MyAppInfo
}