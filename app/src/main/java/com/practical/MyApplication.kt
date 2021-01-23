package com.practical

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.androidnetworking.AndroidNetworking
import okhttp3.OkHttpClient
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 * Created by Hardik(22/01/2021)
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
            .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
