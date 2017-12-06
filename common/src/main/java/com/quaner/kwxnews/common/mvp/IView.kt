package com.quaner.wxnews.common.mvp

import android.content.Context
import android.content.Intent

/**
 * Created by wenxin on 2017/11/27.
 */

interface IView {

    val ctx: Context

    fun launchActivity(intent: Intent)

    fun killMyself()

    fun showLoading(msg: String)

    fun showLoading(id: Int)

    fun hideLoading()
}
