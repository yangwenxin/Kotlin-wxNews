package com.quaner.kwxnews.ui.fragment

import android.view.View
import com.quaner.kwxnews.MyApplication
import com.quaner.kwxnews.R
import com.quaner.kwxnews.common.base.BaseFragment
import com.quaner.kwxnews.common.http.Api
import com.quaner.kwxnews.di.component.DaggerGankComponent
import com.quaner.kwxnews.di.module.GankModule
import com.quaner.kwxnews.mvp.contract.GankContract
import com.quaner.kwxnews.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by wenxin on 2017/12/1.
 */
class HomeFragment : BaseFragment<HomePresenter>(), GankContract.View {

    override fun init() {
        btn.setOnClickListener(View.OnClickListener { view ->
            mPresenter.getData(type, 1, Api.FLAG_REFRESH)
        })
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

    override fun layoutid(): Int = R.layout.fragment_home


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        val type: String = "福利"
    }

}