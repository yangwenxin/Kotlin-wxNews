package com.quaner.kwxnews.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quaner.wxnews.common.base.BaseActivity
import com.quaner.wxnews.common.mvp.BasePresenter
import com.quaner.wxnews.common.mvp.IView
import javax.inject.Inject

/**
 * Created by wenxin on 2017/11/29.
 */
open abstract class BaseFragment<P : BasePresenter> : Fragment(), IView {
    override val ctx: Context
        get() = activity

    @Inject protected lateinit var mPresenter: P

    open lateinit var activity: BaseActivity

    private lateinit var mFragmentView: View

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentView = inflater!!.inflate(layoutid(), container, false)
        inject()
        return mFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        loadData()
    }

    abstract fun init()

    abstract fun loadData()

    abstract fun inject()

    abstract fun layoutid(): Int

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity.finish()
    }

    override fun showLoading(msg: String) {
        activity.showLoading(msg)
    }

    override fun showLoading(id: Int) {
        activity.showLoading(id)
    }

    override fun hideLoading() {
        activity.hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.onDestroy()
        if (mFragmentView.parent != null && mFragmentView.parent is ViewGroup)
            (mFragmentView.parent!! as ViewGroup).removeView(mFragmentView)
    }
}