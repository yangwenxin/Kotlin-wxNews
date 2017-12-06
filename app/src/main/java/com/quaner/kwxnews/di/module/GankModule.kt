package com.quaner.kwxnews.di.module

import com.quaner.kwxnews.mvp.contract.GankContract
import dagger.Module
import dagger.Provides

/**
 * Created by wenxin on 2017/12/1.
 */
@Module
class GankModule(private var view: GankContract.View) {

    @Provides
    fun provideView() = view

}