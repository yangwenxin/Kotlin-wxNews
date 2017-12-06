package com.quaner.kwxnews.common.http

/**
 * Created by wenxin on 2017/12/1.
 */
open class HttpResult<T>(val error: Boolean,
                         var results: T)
