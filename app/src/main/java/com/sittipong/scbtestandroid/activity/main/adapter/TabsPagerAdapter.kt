package com.sittipong.scbtestandroid.activity.main.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.activity.main.OnRefreshContent
import com.sittipong.scbtestandroid.fragment.mobilelist.MobileFavoriteFragment
import com.sittipong.scbtestandroid.fragment.mobilelist.views.MobileListFragment

class TabsPagerAdapter(var ctx: Context, fm: FragmentManager, private val onRefreshCallback: OnRefreshContent) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                MobileListFragment.newInstance(onRefreshCallback)
            }
            1 -> MobileFavoriteFragment.newInstance(onRefreshCallback)
            else -> {
                return null
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> ctx.resources.getString(R.string.mobile_list)
            1 -> ctx.resources.getString(R.string.favorite_list)
            else -> {
                return ""
            }
        }
    }
}