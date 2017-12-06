package com.quaner.kwxnews.common.di.component

import android.content.Context
import com.quaner.kwxnews.common.di.module.AppModule
import com.quaner.kwxnews.common.di.module.HttpModule
import com.quaner.kwxnews.di.module.ServiceModule
import com.quaner.kwxnews.http.ServiceManager
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by wenxin on 2017/12/1.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, HttpModule::class, ServiceModule::class))
interface AppComponent {

    fun application(): Context

    fun retrofit(): Retrofit

    fun ServiceManager(): ServiceManager

}
