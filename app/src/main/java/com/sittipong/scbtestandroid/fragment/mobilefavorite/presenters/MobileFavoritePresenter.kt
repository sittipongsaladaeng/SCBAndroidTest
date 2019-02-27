package com.sittipong.scbtestandroid.fragment.mobilefavorite.presenters

import com.sittipong.scbtestandroid.fragment.mobilefavorite.views.MobileFavoriteView
import com.sittipong.scbtestandroid.model.MobileData

interface MobileFavoritePresenter {
    fun bindView(view: MobileFavoriteView)
    fun unBind()
    fun queryMobileList()
    fun nextToMobileDetailPage(data: MobileData)
    fun delete(data: MobileData)
    fun showDialogSort(sortId: Int)
    fun sortMobileData(sortId: Int)
}