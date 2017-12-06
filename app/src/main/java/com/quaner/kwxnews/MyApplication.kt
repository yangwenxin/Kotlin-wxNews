package com.quaner.kwxnews

import android.content.Context
import com.quaner.kwxnews.common.CommonApplication
import com.quaner.kwxnews.common.di.component.AppComponent
import com.quaner.kwxnews.common.di.component.DaggerAppComponent
import com.quaner.kwxnews.common.di.module.AppModule
import com.quaner.kwxnews.common.di.module.HttpModule

/**
 * Created by wenxin on 2017/12/1.
 */
open class MyApplication : CommonApplication() {
    private lateinit var appModule: AppModule
    private lateinit var httpModule: HttpModule
    internal lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }

        context = applicationContext
        //提供application
        appModule = AppModule(this)
        httpModule = HttpModule()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(appModule)
                .httpModule(httpModule)
                .build()

    }

    companion object {
        var context: Context? = null
            private set
        var instance: MyApplication? = null
            private set

    }
}