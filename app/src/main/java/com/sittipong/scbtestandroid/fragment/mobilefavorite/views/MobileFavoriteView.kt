package com.sittipong.scbtestandroid.fragment.mobilefavorite.views

import com.sittipong.scbtestandroid.model.MobileData

interface MobileFavoriteView {
    fun onQuerySuccess(datas: MutableList<MobileData>)
    fun onEmpty()
    fun onNextToMobileDetailPage(data: MobileData)
    fun onDeleteSuccess()
    fun onShowDialogSort(sortId: Int)
    fun onSortMobileData(sortId: Int)
}