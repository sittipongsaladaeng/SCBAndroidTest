package com.sittipong.scbtestandroid.fragment.mobilelist.views

import com.sittipong.scbtestandroid.model.MobileData

interface MobileView {
    fun showProgress()
    fun dismissProgress()
    fun onLoadMobileDataSuccess(datas: MutableList<MobileData>)
    fun onLoadMobileDataFailed(code: Int, msg: String)
    fun onNextToMobileDetailPage(data: MobileData)
    fun onShowDialogSort(sortId: Int)
    fun onSortMobileData(sortId: Int)
    fun onAddFavoriteSuccess()
}