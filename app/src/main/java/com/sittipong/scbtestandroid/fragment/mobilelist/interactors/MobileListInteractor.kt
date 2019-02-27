package com.sittipong.scbtestandroid.fragment.mobilelist.interactors

import com.sittipong.scbtestandroid.fragment.mobilelist.interfaces.OnCallServiceMobileListener
import com.sittipong.scbtestandroid.model.MobileData

interface MobileListInteractor {

    fun callServiceMobile(listener: OnCallServiceMobileListener)
    abstract fun addFavoriteToDatabase(listener: OnCallServiceMobileListener, data: MobileData)
}