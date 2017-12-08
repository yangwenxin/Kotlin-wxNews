package com.quaner.wxnews.common.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.quaner.wxnews.common.mvp.IView

/**
 * Created by wenxin on 2017/11/28.
 */
open abstract class BaseActivity : AppCompatActivity(), IView {

    override val ctx: Context
        get() = this

    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setCancelable(false)
        inject()
        if (intent.extras != null) {
            initBundleExtras(intent.extras)
        }
        init()
        loadData()
    }

    abstract fun initBundleExtras(extras: Bundle)

    abstract fun inject()

    abstract fun init()

    abstract fun loadData()

    abstract fun layoutId(): Int

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        finish()
    }

    override fun showLoading(msg: String) {

        if (msg.isEmpty()) {
            mProgressDialog.setMessage("正在加载...")
        } else {
            mProgressDialog.setMessage(msg)
        }
        if (!mProgressDialog.isShowing) {
            mProgressDialog.show()
        }
    }

    override fun showLoading(id: Int) {
        showLoading(getString(id))
    }

    override fun hideLoading() {
        mProgressDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }
}