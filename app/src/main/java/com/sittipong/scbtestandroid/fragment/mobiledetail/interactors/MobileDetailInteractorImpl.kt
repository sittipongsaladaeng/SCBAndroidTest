package com.sittipong.scbtestandroid.fragment.mobilelist.interactors

import com.sittipong.scbtestandroid.di.prepareService
import com.sittipong.scbtestandroid.fragment.mobiledetail.interfaces.OnCallServiceImageMobileListener
import com.sittipong.scbtestandroid.model.ImageMobileData
import com.sittipong.scbtestandroid.service.APIService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MobileDetailInteractorImpl : MobileDetailInteractor {

    var retrofit = prepareService()

    override fun callServiceImageMobile(id: Int, listener: OnCallServiceImageMobileListener) {
        var service = retrofit?.create(APIService::class.java)
        var observable: Observable<Response<MutableList<ImageMobileData>>>? = service?.getImagesMobile(id)
        observable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ response ->
                if (response.isSuccessful) {
                    listener.onCallServiceImageMobileSuccess(response.body()!!)
                } else {
                    listener.onCallServiceImageMobileError(response.code(), response.message())
                }
            }, { _ ->
                listener.onCallServiceImageMobileError(-1, "Can't load data, Please try again.")
            })
    }
}