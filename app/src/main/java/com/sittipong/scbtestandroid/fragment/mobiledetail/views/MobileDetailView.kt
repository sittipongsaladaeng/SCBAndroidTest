package com.sittipong.scbtestandroid.fragment.mobiledetail.views

import com.sittipong.scbtestandroid.model.ImageMobileData
import com.sittipong.scbtestandroid.model.MobileData

interface MobileDetailView {
    fun showProgress()
    fun dismissProgress()
    fun onLoadImageMobileSuccess(images: MutableList<ImageMobileData>)
    fun onLoadImageMobileFailed(code: Int, msg: String)
    fun onSetTextData(data: MobileData)
}