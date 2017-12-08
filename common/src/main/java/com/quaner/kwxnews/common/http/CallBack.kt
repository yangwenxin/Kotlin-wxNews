package com.quaner.kwxnews.common.http

import android.os.Bundle
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.quaner.wxnews.common.mvp.IView
import com.quaner.wxnews.common.utils.ToastUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by wenxin
 */
abstract class CallBack<T> : Observer<T> {

    private var view: IView? = null
    private var msg: String? = null
    private var id = -1

    var mHandler: ProgressDialogHandler? = null

    constructor() {

    }

    constructor(view: IView, msg: String?) {
        this.view = view
        this.msg = msg
        mHandler = ProgressDialogHandler(view)
    }

    constructor(view: IView, id: Int) {
        this.view = view
        this.id = id
        mHandler = ProgressDialogHandler(view)
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {

        Log.e("出错了 -------------- ", e.toString())
        if (view != null) {
            hideLoading()
        }
        if (e is SocketTimeoutException) {
            ToastUtils.showShort("网络异常，请检查网络后重试")
        } else if (e is HttpException || e is UnknownHostException) {
        } else {
            ToastUtils.showShort(e.message!!)
        }
        onFailure()
    }

    override fun onSubscribe(d: Disposable) {
        if (view != null) {
            if (id == -1)
                showLoading(this!!.msg!!)
            else
                showLoading(id)
        }
        addDisposable(d)
    }

    override fun onComplete() {
        if (view != null) {
            hideLoading()
        }
    }


    private fun showLoading(id: Int) {
        val message = mHandler!!.obtainMessage(ProgressDialogHandler.SHOW_ID_DIALOG)
        val bundle = Bundle()
        bundle.putInt("id", id)
        message.data = bundle
        message.sendToTarget()
    }

    private fun showLoading(msg: String) {
        val message = mHandler!!.obtainMessage(ProgressDialogHandler.SHOW_MSG_DIALOG)
        val bundle = Bundle()
        bundle.putString("msg", msg)
        message.data = bundle
        message.sendToTarget()
    }


    private fun hideLoading() {
        mHandler!!.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget()
        mHandler = null
        view = null
    }


    abstract fun addDisposable(d: Disposable)

    abstract fun onFailure()

    abstract fun onSuccess(t: T)
}
