package com.quaner.kwxnews.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.quaner.kwxnews.MyApplication
import com.quaner.kwxnews.R
import com.quaner.kwxnews.common.base.BaseFragment
import com.quaner.kwxnews.di.component.DaggerGankComponent
import com.quaner.kwxnews.di.module.GankModule
import com.quaner.kwxnews.mvp.contract.GankContract
import com.quaner.kwxnews.mvp.presenter.GankPresenter
import com.quaner.kwxnews.ui.activity.WebViewActivity
import com.quaner.kwxnews.ui.adapter.HomeAdapter
import com.quaner.kwxnews.ui.entity.GankEntity
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * Created by wenxin on 2017/12/1.
 */
class HomeFragment : BaseFragment<GankPresenter>(), GankContract.View {


    var alpha = 0
    var scrollerY = 0
    var mAdapter: HomeAdapter? = null

    override fun setAdapter(results: List<GankEntity>) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rl_content.layoutManager = layoutManager
        mAdapter = HomeAdapter(results, activity)
        rl_content.adapter = mAdapter
        rl_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollerY += dy
                alpha = if (Math.abs(scrollerY) > 1000) {
                    100
                } else {
                    Math.abs(scrollerY) / 10
                }
                bl_view.setBlurredLevel(alpha)
            }
        })
        mAdapter!!.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(data: GankEntity, position: Int) {
                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra("title", data.desc)
                intent.putExtra("url", data.url)
                startActivity(intent)
            }
        })
    }

    override fun init() {
        srl_layout.isRefreshing = true
        srl_layout.setOnRefreshListener {
            mPresenter.getData(type, Random().nextInt(30))
        }
    }

    override fun hideLoading() {
        if (srl_layout.isRefreshing) {
            srl_layout.isRefreshing = false
        }
    }

    override fun loadData() {
        mPresenter.getData(type, Random().nextInt(30))
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
        val type: String = "Android"
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}