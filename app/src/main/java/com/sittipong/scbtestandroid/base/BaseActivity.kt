package com.sittipong.scbtestandroid.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.sittipong.scbtestandroid.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            ?.replace(R.id.container, fragment, null)
            ?.commit()
    }
}