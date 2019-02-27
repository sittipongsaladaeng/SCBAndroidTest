package com.sittipong.scbtestandroid.fragment.mobilelist.interactors

import android.util.Log
import com.sittipong.scbtestandroid.di.prepareService
import com.sittipong.scbtestandroid.fragment.mobilelist.interfaces.OnCallServiceMobileListener
import com.sittipong.scbtestandroid.model.MobileData
import com.sittipong.scbtestandroid.service.APIService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import retrofit2.Response

class MobileListInteractorImpl : MobileListInteractor {

    var retrofit = prepareService()


    override fun callServiceMobile(listener: OnCallServiceMobileListener) {
        var service = retrofit?.create(APIService::class.java)
        var observable: Observable<Response<MutableList<MobileData>>>? = service?.getMobiles()
        observable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ response ->
                if (response.isSuccessful) {
                    listener.onCallServiceMobileSuccess(response.body()!!)
                } else {
                    listener.onCallServiceMobileError(response.code(), response.message())
                }
            }, { _ ->
                listener.onCallServiceMobileError(-1, "Can't load data, Please try again.")
            })
    }

    override fun addFavoriteToDatabase(listener: OnCallServiceMobileListener, data: MobileData) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.executeTransaction { realm ->
                realm.copyToRealmOrUpdate(data)
                listener.onAddFavoriteSuccess()
            }
        } finally {
            realm.close()
        }
    }
}