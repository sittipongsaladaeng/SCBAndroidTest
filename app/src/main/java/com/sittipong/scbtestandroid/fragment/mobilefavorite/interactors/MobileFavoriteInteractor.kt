package com.sittipong.scbtestandroid.fragment.mobilefavorite.interactors

import com.sittipong.scbtestandroid.fragment.mobilefavorite.interfaces.OnQuerySuccess
import com.sittipong.scbtestandroid.model.MobileData

interface MobileFavoriteInteractor {
    fun queryMobileFavorite(listener: OnQuerySuccess)
    fun deleteFavorite(item: MobileData, listener: OnQuerySuccess)
}