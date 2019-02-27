package com.sittipong.scbtestandroid.service

import com.sittipong.scbtestandroid.model.ImageMobileData
import com.sittipong.scbtestandroid.model.MobileData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("api/mobiles/")
    fun getMobiles(): Observable<Response<MutableList<MobileData>>>

    @GET("api/mobiles/{mobile_id}/images/")
    fun getImagesMobile(@Path("mobile_id") mobileId: Int): Observable<Response<MutableList<ImageMobileData>>>
}