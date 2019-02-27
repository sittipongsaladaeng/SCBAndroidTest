package com.sittipong.scbtestandroid.fragment.mobiledetail.interfaces

import com.sittipong.scbtestandroid.model.ImageMobileData

interface OnCallServiceImageMobileListener {

    fun onCallServiceImageMobileSuccess(datas: MutableList<ImageMobileData>)

    fun onCallServiceImageMobileError(code: Int, msg: String)
}