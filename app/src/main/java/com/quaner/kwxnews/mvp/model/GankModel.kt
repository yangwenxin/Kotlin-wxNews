package com.quaner.kwxnews.mvp.model

import com.quaner.kwxnews.common.http.HttpResult
import com.quaner.kwxnews.http.ServiceManager
import com.quaner.kwxnews.mvp.contract.GankContract
import com.quaner.kwxnews.ui.entity.GankEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by wenxin on 2017/12/1.
 */
class GankModel : GankContract.Model {

    private var serviceManager: ServiceManager

    @Inject
    constructor(serviceManager: ServiceManager) {
        this.serviceManager = serviceManager
    }


    override fun getData(type: String, page: Int): Observable<HttpResult<List<GankEntity>>> {
        return serviceManager.getCommonService().getData(type, page)
    }

    override fun onDestroy() {
    }
}