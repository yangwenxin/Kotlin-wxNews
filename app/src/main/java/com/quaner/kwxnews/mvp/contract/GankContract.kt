package com.quaner.kwxnews.mvp.contract

import com.quaner.kwxnews.common.http.HttpResult
import com.quaner.kwxnews.ui.entity.GankEntity
import com.quaner.wxnews.common.mvp.IModel
import com.quaner.wxnews.common.mvp.IView
import io.reactivex.Observable

/**
 * Created by wenxin on 2017/12/1.
 */
interface GankContract {

    interface View : IView {

    }

    interface Model : IModel {
        fun getData(type: String, page: Int): Observable<HttpResult<List<GankEntity>>>
    }
}