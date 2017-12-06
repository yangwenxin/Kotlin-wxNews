package com.quaner.kwxnews.http

import com.quaner.kwxnews.http.api.CommonService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by wenxin on 2017/12/1.
 */
@Singleton
open class ServiceManager {

    private var commonService: CommonService

    @Inject
    constructor(commonService: CommonService) {
        this.commonService = commonService
    }

    fun getCommonService(): CommonService = commonService
}