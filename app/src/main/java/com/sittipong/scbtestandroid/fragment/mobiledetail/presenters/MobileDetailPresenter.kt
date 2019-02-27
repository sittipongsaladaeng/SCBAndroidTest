package com.sittipong.scbtestandroid.fragment.mobiledetail.presenters

import com.sittipong.scbtestandroid.fragment.mobiledetail.views.MobileDetailView
import com.sittipong.scbtestandroid.model.MobileData

interface MobileDetailPresenter {
    fun bindView(view: MobileDetailView)
    fun unBind()
    fun callServiceImageMobile(id: Int)
    fun setTextData(data:MobileData)
}