package com.sittipong.scbtestandroid.fragment.mobilefavorite.interactors

import com.sittipong.scbtestandroid.fragment.mobilefavorite.interfaces.OnQuerySuccess
import com.sittipong.scbtestandroid.model.MobileData
import io.realm.Realm


class MobileFavoriteInteractorImpl : MobileFavoriteInteractor {

    override fun queryMobileFavorite(listener: OnQuerySuccess) {
        val results = Realm.getDefaultInstance().where(MobileData::class.java).findAll()
        if (results.size == 0)
            listener.onEmpty()
        else
            listener.onQuerySuccess(results.toMutableList())
    }

    override fun deleteFavorite(item: MobileData, listener: OnQuerySuccess) {
        Realm.getDefaultInstance().executeTransaction(Realm.Transaction { realm ->
            val results = realm.where(MobileData::class.java!!).equalTo("id", item.id).findAll()
            results.deleteAllFromRealm()
            listener.onDeleteSuccess()
        })
    }
}