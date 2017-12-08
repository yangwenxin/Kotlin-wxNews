package com.quaner.kwxnews.common.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.Toolbar
import android.support.v7.widget.Toolbar.OnMenuItemClickListener
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.quaner.wxnews.common.R


class DesignToolbar : Toolbar {
    private var mTitleTextView: TextView? = null
    //是否显示按钮nav
    private var mNavIsButton = true
    private var mNavButtonIcon: Drawable? = null
    private var mTitle: CharSequence? = null
    private var mShowRightButton = false
    private var mRightButtonText: CharSequence? = null
    private var mRightButtonIcon: Drawable? = null

    private var mOnRightMenuClickListener: OnRightMenuClickListener? = null


    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttributes(attrs, context.obtainStyledAttributes(attrs,
                R.styleable.DesignToolbar), context)
    }

    private fun getAttributes(attrs: AttributeSet, a: TypedArray, context: Context) {
        mTitleTextView = TextView(context)
        mTitleTextView!!.setSingleLine()
        mTitleTextView!!.ellipsize = TextUtils.TruncateAt.END
        mTitleTextView!!.gravity = Gravity.CENTER
        setTitleTextColor(resources.getColor(R.color.text_black_normal))
        try {
            mNavIsButton = a.getBoolean(R.styleable.DesignToolbar_navigationIsButton, false)
            mNavButtonIcon = a.getDrawable(R.styleable.DesignToolbar_navigationLeftIcon)
            mTitle = a.getString(R.styleable.DesignToolbar_titleText)
            mShowRightButton = a.getBoolean(R.styleable.DesignToolbar_showRightButton, false)
            mRightButtonText = a.getString(R.styleable.DesignToolbar_rightText)
            mRightButtonIcon = a.getDrawable(R.styleable.DesignToolbar_rightIcon)
        } finally {
            a.recycle()
        }

        setShowRightButton(mShowRightButton)


        setShowNabButton(mNavIsButton)
        val a2 = context.obtainStyledAttributes(attrs,
                android.support.v7.appcompat.R.styleable.Toolbar)
        try {
            mTitleTextView!!.setTextAppearance(context, a.getResourceId(android.support.v7.appcompat.R.styleable.Toolbar_titleTextAppearance, 0))
        } finally {
            a2.recycle()
        }


        setTitle("")
        subtitle = ""
        addTitleView()
    }

    private fun addTitleView() {
        val lp = super.generateDefaultLayoutParams()
        val params = lp as Toolbar.LayoutParams
        params.gravity = Gravity.CENTER
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        setTitleText(mTitle)
        addView(mTitleTextView, params)
    }


    override fun setTitle(resId: Int) {
        super.setTitle(resId)
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
    }


    fun setTitleText(title: CharSequence?) {
        mTitle = title
        if (mTitleTextView != null) {
            mTitleTextView!!.text = mTitle
        }
    }

    fun setTitleText(resId: Int) {
        mTitle = resources.getString(resId)
        if (mTitleTextView != null) {
            mTitleTextView!!.text = mTitle
        }
    }

    fun setRightButtonText(rightButtonText: CharSequence?) {
        this.mRightButtonText = rightButtonText
        if (mShowRightButton) {
            if (!TextUtils.isEmpty(mRightButtonText)) {
                menu.getItem(0).isVisible = true
                menu.getItem(0).icon = null
                menu.getItem(0).title = mRightButtonText!!.toString()
            }
        }
    }

    fun setRightButtonIcon(drawable: Drawable?) {
        this.mRightButtonIcon = drawable
        if (mShowRightButton)
            if (mRightButtonIcon != null) {
                menu.getItem(0).isVisible = true
                menu.getItem(0).icon = mRightButtonIcon
            }
    }


    fun setRightButtonIcon(resourceId: Int) {
        val drawable = ResourcesCompat.getDrawable(resources, resourceId, null)
        setRightButtonIcon(drawable)
    }

    fun setShowRightButton(isShown: Boolean) {
        this.mShowRightButton = isShown
        menu.clear()
        if (this.mShowRightButton) {
            inflateMenu(R.menu.menu)
            setRightButtonText(mRightButtonText)
            setRightButtonIcon(mRightButtonIcon)
            setOnMenuItemClickListener(OnMenuItemClickListener { item ->
                if (item.itemId == R.id.action_right_text && mOnRightMenuClickListener != null) {
                    mOnRightMenuClickListener!!.onRightClick()
                    return@OnMenuItemClickListener true
                }
                false
            })
        }
    }

    fun setShowNabButton(showNabButton: Boolean) {
        this.mNavIsButton = showNabButton
        if (mNavIsButton) {
            navigationIcon = mNavButtonIcon
        } else {
            navigationIcon = null
        }
    }


    fun setOnRightMenuClickListener(
            onRightMenuClickListener: OnRightMenuClickListener) {
        mOnRightMenuClickListener = onRightMenuClickListener
    }


    override fun setTitleTextColor(color: Int) {
        mTitleTextView!!.setTextColor(color)
    }

    override fun setNavigationIcon(icon: Drawable?) {
        super.setNavigationIcon(icon)
    }

    override fun setNavigationIcon(resId: Int) {
        super.setNavigationIcon(resId)
    }

    override fun setNavigationOnClickListener(listener: View.OnClickListener) {
        super.setNavigationOnClickListener(listener)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }


    interface OnRightMenuClickListener {
        fun onRightClick()
    }

}
