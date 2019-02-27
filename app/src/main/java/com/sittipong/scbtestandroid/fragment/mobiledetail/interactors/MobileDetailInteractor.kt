package com.sittipong.scbtestandroid.fragment.mobilelist.interactors

import com.sittipong.scbtestandroid.fragment.mobiledetail.interfaces.OnCallServiceImageMobileListener

interface MobileDetailInteractor {

    fun callServiceImageMobile(id: Int, listener: OnCallServiceImageMobileListener)
}