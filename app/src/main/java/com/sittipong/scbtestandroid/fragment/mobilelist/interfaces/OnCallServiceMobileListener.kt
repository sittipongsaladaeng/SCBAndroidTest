package com.sittipong.scbtestandroid.fragment.mobilelist.interfaces

import com.sittipong.scbtestandroid.model.MobileData

interface OnCallServiceMobileListener {

    fun onCallServiceMobileSuccess(datas: MutableList<MobileData>)

    fun onCallServiceMobileError(code: Int, msg: String)

    fun onAddFavoriteSuccess()
}