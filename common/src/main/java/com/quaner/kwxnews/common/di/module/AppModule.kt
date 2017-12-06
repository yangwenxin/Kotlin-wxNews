package com.quaner.kwxnews.common.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by wenxin on 2017/12/1.
 */
@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext() = context
}
