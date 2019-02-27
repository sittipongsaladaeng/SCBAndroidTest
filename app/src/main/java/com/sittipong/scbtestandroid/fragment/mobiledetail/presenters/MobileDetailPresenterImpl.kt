package com.sittipong.scbtestandroid.fragment.mobiledetail.presenters

import com.sittipong.scbtestandroid.fragment.mobiledetail.interfaces.OnCallServiceImageMobileListener
import com.sittipong.scbtestandroid.fragment.mobiledetail.views.MobileDetailView
import com.sittipong.scbtestandroid.fragment.mobilelist.interactors.MobileDetailInteractor
import com.sittipong.scbtestandroid.fragment.mobilelist.interactors.MobileDetailInteractorImpl
import com.sittipong.scbtestandroid.model.ImageMobileData
import com.sittipong.scbtestandroid.model.MobileData

class MobileDetailPresenterImpl : MobileDetailPresenter, OnCallServiceImageMobileListener {

    var view: MobileDetailView? = null
    var interactor: MobileDetailInteractor = MobileDetailInteractorImpl()

    override fun bindView(view: MobileDetailView) {
        this@MobileDetailPresenterImpl.view = view
    }

    override fun unBind() {
        this@MobileDetailPresenterImpl.view = null
    }

    override fun callServiceImageMobile(id: Int) {
        this@MobileDetailPresenterImpl.view?.showProgress()
        this@MobileDetailPresenterImpl.interactor.callServiceImageMobile(id, this@MobileDetailPresenterImpl)
    }

    override fun onCallServiceImageMobileSuccess(images: MutableList<ImageMobileData>) {
        this@MobileDetailPresenterImpl.view?.dismissProgress()
        this@MobileDetailPresenterImpl.view?.onLoadImageMobileSuccess(images)
    }

    override fun onCallServiceImageMobileError(code: Int, msg: String) {
        this@MobileDetailPresenterImpl.view?.dismissProgress()
        this@MobileDetailPresenterImpl.view?.onLoadImageMobileFailed(code, msg)
    }

    override fun setTextData(data: MobileData) {
        this@MobileDetailPresenterImpl.view?.onSetTextData(data)
    }
}