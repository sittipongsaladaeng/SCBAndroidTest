package com.sittipong.scbtestandroid.fragment.mobilefavorite.interfaces

import com.sittipong.scbtestandroid.model.MobileData

interface OnQuerySuccess {
    fun onQuerySuccess(datas: MutableList<MobileData>)
    fun onDeleteSuccess()
    fun onEmpty()
}