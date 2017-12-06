package com.quaner.kwxnews.common.http

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.quaner.wxnews.common.mvp.IView


/**
 * Created by wenxin
 */
class ProgressDialogHandler(private var view: IView?) : Handler() {

    private val pd: ProgressDialog? = null

    override fun handleMessage(msg: Message) {
        var data: Bundle? = null
        when (msg.what) {
            SHOW_MSG_DIALOG -> {
                data = msg.data
                showLoading(data!!.getString("msg"))
            }
            SHOW_ID_DIALOG -> {
                data = msg.data
                showLoading(data!!.getInt("id"))
            }
            DISMISS_PROGRESS_DIALOG -> hideLoading()
        }
    }

    fun showLoading(msg: String?) {
        view!!.showLoading(msg!!)
    }

    fun showLoading(id: Int) {
        view!!.showLoading(id)
    }

    fun hideLoading() {
        view!!.hideLoading()
        view = null
    }

    companion object {


        val SHOW_MSG_DIALOG = 1
        val SHOW_ID_DIALOG = 2
        val DISMISS_PROGRESS_DIALOG = 3
    }
}
