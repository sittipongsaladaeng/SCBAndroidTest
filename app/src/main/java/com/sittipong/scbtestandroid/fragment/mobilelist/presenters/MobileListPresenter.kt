package com.sittipong.scbtestandroid.fragment.mobilelist.presenters

import com.sittipong.scbtestandroid.fragment.mobilelist.views.MobileView
import com.sittipong.scbtestandroid.model.MobileData

interface MobileListPresenter {
    fun bindView(view: MobileView)
    fun unBind()
    fun callServiceMobileList()
    fun nextToMobileDetailPage(data: MobileData)
    fun showDialogSort(sortId: Int)
    fun sortMobileData(sortId: Int)
    fun addFavorite(data: MobileData)
}