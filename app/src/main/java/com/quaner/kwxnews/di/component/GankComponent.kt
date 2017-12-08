package com.quaner.kwxnews.di.component

import com.quaner.kwxnews.common.di.component.AppComponent
import com.quaner.kwxnews.common.di.scope.FragmentScope
import com.quaner.kwxnews.di.module.GankModule
import com.quaner.kwxnews.ui.fragment.FindFragment
import com.quaner.kwxnews.ui.fragment.HomeFragment
import dagger.Component

/**
 * Created by wenxin on 2017/12/1.
 */
@FragmentScope
@Component(modules = arrayOf(GankModule::class), dependencies = arrayOf(AppComponent::class))
interface GankComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(findFragment: FindFragment)
}