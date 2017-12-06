package com.quaner.kwxnews.common

import android.app.Application
import android.content.Context
import android.os.Environment
import java.io.File


/**
 * Created by wenxin
 */
open class CommonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }

        context = applicationContext
    }

    override fun getCacheDir(): File {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val cacheDir = externalCacheDir
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir
            }
        }
        return super.getCacheDir()
    }

    companion object {
        var context: Context? = null
            private set
        var instance: CommonApplication? = null
            private set

    }
}
