package com.quaner.kwxnews.ui.activity

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.quaner.kwxnews.R
import com.quaner.kwxnews.ui.fragment.HomeFragment
import com.quaner.wxnews.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var currentFragment: Fragment
    override fun inject() {

    }

    override fun init() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initFragments()
    }

    private fun initFragments() {
        homeFragment = HomeFragment.newInstance()

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
                if (currentFragment !is HomeFragment)
                    addFragment()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                currentFragment = Fragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
