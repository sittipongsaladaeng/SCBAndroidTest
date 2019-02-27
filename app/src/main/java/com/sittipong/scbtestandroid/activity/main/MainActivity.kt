package com.sittipong.scbtestandroid.activity.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.activity.main.adapter.TabsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import com.sittipong.scbtestandroid.base.BaseActivity
import com.sittipong.scbtestandroid.model.MobileData
import org.greenrobot.eventbus.EventBus
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener


class MainActivity : BaseActivity(), OnRefreshContent {

    var currentIndex: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragmentAdapter = TabsPagerAdapter(applicationContext, supportFragmentManager, this@MainActivity)
        viewpager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager)
        viewpager.addOnPageChangeListener(onPageChangeListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_sort -> {
                showDialogSort()
            }
        }
        return true
    }

    private fun showDialogSort() {
        EventBus.getDefault().post(currentIndex.toString())
    }

    var onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

        }

        override fun onPageSelected(pos: Int) {
            currentIndex = pos
        }
    }

    override fun refresh() {
        EventBus.getDefault().post("2")
    }
}


