package com.quaner.kwxnews.http.api

import com.quaner.kwxnews.common.http.HttpResult
import com.quaner.kwxnews.ui.entity.GankEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by wenxin on 2017/12/1.
 */
interface CommonService {

    //    http://gank.io/api/data/Android/10/1
    //    http://gank.io/api/data/福利/10/1
    //    http://gank.io/api/data/iOS/20/2
    //    http://gank.io/api/data/all/20/2
    @GET("data/{type}/20/{page}")
    fun getData(@Path("type") type: String, @Path("page") page: Int): Observable<HttpResult<List<GankEntity>>>


}