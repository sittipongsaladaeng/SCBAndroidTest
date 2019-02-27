package com.sittipong.scbtestandroid.activity.mobiledetail

import android.os.Bundle
import android.view.MenuItem
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.base.BaseActivity
import com.sittipong.scbtestandroid.fragment.mobiledetail.views.ARG_MOBILE_DATA
import com.sittipong.scbtestandroid.fragment.mobiledetail.views.MobileDetailFragment
import com.sittipong.scbtestandroid.model.MobileData
import kotlinx.android.synthetic.main.activity_main.*


class MobileDetailActivity : BaseActivity() {

    var mobileData: MobileData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        intent.let {
            mobileData = it.getParcelableExtra(ARG_MOBILE_DATA)
        }
        replaceFragment(MobileDetailFragment.newInstance(mobileData))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}