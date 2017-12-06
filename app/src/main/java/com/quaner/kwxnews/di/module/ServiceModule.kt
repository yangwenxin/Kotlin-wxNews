package com.quaner.kwxnews.di.module

import com.quaner.kwxnews.http.api.CommonService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by wenxin on 2017/12/1.
 */
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideCommonService(retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }
}