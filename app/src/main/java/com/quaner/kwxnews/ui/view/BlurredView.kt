package com.quaner.kwxnews.ui.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.quaner.kwxnews.R
import com.quaner.kwxnews.ui.utils.BitmapUtil
import com.quaner.kwxnews.ui.utils.BlurBitmapUtil

class BlurredView : RelativeLayout {

    /*========== 全局相关 ==========*/

    //上下文对象
    private var mContext: Context? = null

    /*========== 图片相关 ==========*/

    //原图ImageView
    private var mOriginImg: ImageView? = null
    //模糊后的ImageView
    private var mBlurredImg: ImageView? = null
    //模糊后的Bitmap
    private var mBlurredBitmap: Bitmap? = null
    //原图Bitmap
    private var mOriginBitmap: Bitmap? = null


    /*========== 属性相关 ==========*/

    //是否禁用模糊效果
    private var isDisableBlurred: Boolean = false

    /*========== 四个构造函数 ==========*/

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
        initAttr(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
        initAttr(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView(context)
        initAttr(context, attrs)
    }

    /**
     * 初始化View
     *
     * @param context 上下文对象
     */
    private fun initView(context: Context) {
        mContext = context
        val inflate = LayoutInflater.from(context).inflate(R.layout.blurredview, this)
        mOriginImg = findViewById(R.id.blurredview_origin_img)
        mBlurredImg = findViewById(R.id.blurredview_blurred_img)
    }

    /**
     * 初始化属性
     *
     * @param context 上下文对象
     * @param attrs   相关属性
     */
    private fun initAttr(context: Context, attrs: AttributeSet) {
        //查找一些属性值
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlurredView)
        val drawable = typedArray.getDrawable(R.styleable.BlurredView_src)
        //默认为false
        isDisableBlurred = typedArray.getBoolean(R.styleable.BlurredView_disableBlurred, false)
        //必须回收 方便重用
        typedArray.recycle()

        // 模糊图片
        if (null != drawable) {
            mOriginBitmap = BitmapUtil.drawableToBitmap(drawable)
            mBlurredBitmap = BlurBitmapUtil.blurBitmap(context, mOriginBitmap!!, BLUR_RADIUS)
        }

        // 设置是否可见
        if (!isDisableBlurred) {
            mBlurredImg!!.visibility = View.VISIBLE
        }
    }

    /**
     * 当所有子View出现后 设置相关内容
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        setImageView()
    }

    /**
     * 填充ImageView
     */
    private fun setImageView() {
        mBlurredImg!!.setImageBitmap(mBlurredBitmap)
        mOriginImg!!.setImageBitmap(mOriginBitmap)
    }

    /**
     * 设置模糊程度
     *
     * @param level 模糊程度, 数值在 0~100 之间.
     */
    fun setBlurredLevel(level: Int) {
        //超过模糊级别范围 直接抛异常
        if (level < 0 || level > 100) {
            throw IllegalStateException("No validate level, the value must be 0~100")
        }

        //禁用模糊直接返回
        if (isDisableBlurred) {
            return
        }

        //设置透明度
        mOriginImg!!.setAlpha((ALPHA_MAX_VALUE - level * 2.55).toInt())
    }

    /**
     * 显示模糊图片
     */
    fun showBlurredView() {
        mBlurredImg!!.visibility = View.VISIBLE
    }

    /**
     * 选择是否启动/禁止模糊效果
     *
     * @param isDisableBlurred 是否禁用模糊效果
     */
    fun setBlurredable(isDisableBlurred: Boolean) {
        if (isDisableBlurred) {
            mOriginImg!!.setAlpha(ALPHA_MAX_VALUE)
            mBlurredImg!!.visibility = View.INVISIBLE
        } else {
            mBlurredImg!!.visibility = View.VISIBLE
        }
    }

    /**
     * 禁用模糊效果
     */
    fun disableBlurredView() {
        isDisableBlurred = true
        mOriginImg!!.setAlpha(ALPHA_MAX_VALUE)
        mBlurredImg!!.visibility = View.INVISIBLE
    }

    /**
     * 启用模糊效果
     */
    fun enableBlurredView() {
        isDisableBlurred = false
        mBlurredImg!!.visibility = View.VISIBLE
    }

    companion object {
        //透明最大值
        private val ALPHA_MAX_VALUE = 255
        //最大模糊度(在0.0到25.0之间)
        private val BLUR_RADIUS = 25f
    }
}
