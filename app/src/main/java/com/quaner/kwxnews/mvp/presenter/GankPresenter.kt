package com.quaner.kwxnews.mvp.presenter

import com.quaner.kwxnews.common.http.CallBack
import com.quaner.kwxnews.common.http.HttpResult
import com.quaner.kwxnews.mvp.contract.GankContract
import com.quaner.kwxnews.mvp.model.GankModel
import com.quaner.kwxnews.ui.entity.GankEntity
import com.quaner.wxnews.common.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by wenon 2017/12/1.
 */
class GankPresenter @Inject
constructor(private val mModel: GankModel,
            private val mView: GankContract.View) : BasePresenter() {

    fun getData(type: String, page: Int) {

        mModel.getData(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallBack<HttpResult<List<GankEntity>>>() {
                    override fun onSuccess(result: HttpResult<List<GankEntity>>) {
                        mView.hideLoading()
                        if (!result.error) {
                            mView.setAdapter(result.results)
                        }
                    }

                    override fun addDisposable(d: Disposable) {
                        addSubscribe(d)
                    }

                    override fun onFailure() {
                        mView.hideLoading()
                    }
                })
    }

}