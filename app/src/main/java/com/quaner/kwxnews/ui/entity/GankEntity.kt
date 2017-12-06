package com.quaner.kwxnews.ui.entity

/**
 * Created by wenxin on 2017/12/1.
 */
class GankEntity(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String,
        var width: Float,
        var height: Float
)