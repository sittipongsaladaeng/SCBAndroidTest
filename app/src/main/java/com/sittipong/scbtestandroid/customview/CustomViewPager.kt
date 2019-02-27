package com.sittipong.scbtestandroid.customview

import android.content.Context
import android.view.MotionEvent
import android.text.method.Touch.onTouchEvent
import android.support.v4.view.ViewPager
import android.util.AttributeSet


class CustomViewPager : ViewPager {

    private var isPagingEnabled = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event)
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }

//    fun setPagingEnabled(b: Boolean) {
//        this.isPagingEnabled = b
//    }
}
