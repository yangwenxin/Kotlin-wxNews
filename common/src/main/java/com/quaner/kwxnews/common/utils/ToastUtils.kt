package com.quaner.wxnews.common.utils

import com.quaner.kwxnews.common.CommonApplication
import android.widget.Toast

/**
 * Created by wenxin
 */
class ToastUtils private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {
        private var toast: Toast? = null //Toast的对象！

        var isShow = true

        /**
         * 短时间显示Toast
         *
         * @param message
         */
        fun showShort(message: CharSequence) {
            if (toast == null) {//改为静态吐司。防止多次点击反复弹出
                toast = Toast.makeText(CommonApplication.context, message, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(message)
            }
            if (isShow)
                toast!!.show()
        }


        /**
         * 长时间显示Toast
         *
         * @param message
         */
        fun showLong(message: CharSequence) {
            if (isShow)
                Toast.makeText(CommonApplication.context, message, Toast.LENGTH_LONG).show()
        }

        /**
         * 长时间显示Toast
         *
         * @param message
         */
        fun showLong(message: Int) {
            if (isShow)
                Toast.makeText(CommonApplication.context, message, Toast.LENGTH_LONG).show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param message
         * @param duration
         */
        fun show(message: CharSequence, duration: Int) {
            if (isShow)
                Toast.makeText(CommonApplication.context, message, duration).show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param message
         * @param duration
         */
        fun show(message: Int, duration: Int) {
            if (isShow)
                Toast.makeText(CommonApplication.context, message, duration).show()
        }
    }

}