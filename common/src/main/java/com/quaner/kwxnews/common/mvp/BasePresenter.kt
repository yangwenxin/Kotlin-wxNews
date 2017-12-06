package com.quaner.wxnews.common.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 * Created by wenxin
 */

open class BasePresenter : IPreseter {

    private var mCompositeDisposable: CompositeDisposable? = null

    constructor() {
        onStart()
    }

    override fun onStart() {
        if (useEventBus())
        //如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this)//注册eventbus
    }

    override fun onDestroy() {
        if (useEventBus())
        //如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this)//解除注册eventbus
        unSubscribe()//解除订阅
        this.mCompositeDisposable = null
    }

    private fun useEventBus(): Boolean {
        return false
    }

    fun addSubscribe(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable)//将所有subscription放入,集中处理
    }

    private fun unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()//保证activity结束时取消所有正在执行的订阅
        }
    }
}
