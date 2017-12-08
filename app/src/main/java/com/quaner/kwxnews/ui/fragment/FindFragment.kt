package com.quaner.kwxnews.ui.fragment

import com.quaner.kwxnews.MyApplication
import com.quaner.kwxnews.R
import com.quaner.kwxnews.common.base.BaseFragment
import com.quaner.kwxnews.di.component.DaggerGankComponent
import com.quaner.kwxnews.di.module.GankModule
import com.quaner.kwxnews.mvp.contract.GankContract
import com.quaner.kwxnews.mvp.presenter.GankPresenter
import com.quaner.kwxnews.ui.entity.GankEntity

/**
 * Created by wenxin on 2017/12/8.
 */

class FindFragment : BaseFragment<GankPresenter>(), GankContract.View {

    override fun setAdapter(results: List<GankEntity>) {

    }

    override fun init() {

    }

    override fun loadData() {

    }

    override fun inject() {
        DaggerGankComponent
                .builder()
                .appComponent(MyApplication.instance!!.appComponent)
                .gankModule(GankModule(this))
                .build()
                .inject(this)
    }

    override fun layoutid(): Int {
        return R.layout.fragment_find
    }

    companion object {
        fun newInstance(): FindFragment = FindFragment()
    }
}
