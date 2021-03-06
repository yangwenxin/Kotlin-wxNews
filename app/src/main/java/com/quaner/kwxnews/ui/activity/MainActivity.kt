package com.quaner.kwxnews.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.quaner.kwxnews.R
import com.quaner.kwxnews.ui.fragment.FindFragment
import com.quaner.kwxnews.ui.fragment.HomeFragment
import com.quaner.wxnews.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    lateinit var homeFragment: HomeFragment
    lateinit var findFragment: FindFragment
    lateinit var currentFragment: Fragment

    override fun inject() {

    }

    override fun init() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initFragments()
    }

    private fun initFragments() {
        homeFragment = HomeFragment.newInstance()
        findFragment = FindFragment.newInstance()
        currentFragment = homeFragment
        addFragment()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_content, currentFragment).commit()
    }

    override fun loadData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (currentFragment !is HomeFragment) {
                    currentFragment = homeFragment
                    homeFragment.scrollerY = 0
                    addFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (currentFragment !is FindFragment) {
                    currentFragment = findFragment
                    addFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun initBundleExtras(extras: Bundle) {

    }
}
