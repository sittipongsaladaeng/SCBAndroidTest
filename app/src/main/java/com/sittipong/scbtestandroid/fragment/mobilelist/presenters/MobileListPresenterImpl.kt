package com.sittipong.scbtestandroid.fragment.mobilelist.presenters

import com.sittipong.scbtestandroid.fragment.mobilelist.interactors.MobileListInteractor
import com.sittipong.scbtestandroid.fragment.mobilelist.interactors.MobileListInteractorImpl
import com.sittipong.scbtestandroid.fragment.mobilelist.interfaces.OnCallServiceMobileListener
import com.sittipong.scbtestandroid.fragment.mobilelist.views.MobileView
import com.sittipong.scbtestandroid.model.MobileData

class MobileListPresenterImpl : MobileListPresenter, OnCallServiceMobileListener {

    var view: MobileView? = null
    var interactor: MobileListInteractor = MobileListInteractorImpl()

    override fun bindView(view: MobileView) {
        this@MobileListPresenterImpl.view = view
    }

    override fun unBind() {
        this@MobileListPresenterImpl.view = null
    }

    override fun callServiceMobileList() {
        this@MobileListPresenterImpl.view?.showProgress()
        this@MobileListPresenterImpl.interactor?.callServiceMobile(this@MobileListPresenterImpl)
    }

    override fun onCallServiceMobileSuccess(datas: MutableList<MobileData>) {
        this@MobileListPresenterImpl.view?.dismissProgress()
        this@MobileListPresenterImpl.view?.onLoadMobileDataSuccess(datas)
    }

    override fun onCallServiceMobileError(code: Int, msg: String) {
        this@MobileListPresenterImpl.view?.dismissProgress()
        this@MobileListPresenterImpl.view?.onLoadMobileDataFailed(code, msg)
    }

    override fun nextToMobileDetailPage(data: MobileData) {
        this@MobileListPresenterImpl.view?.onNextToMobileDetailPage(data)
    }

    override fun showDialogSort(sortId: Int) {
        this@MobileListPresenterImpl.view?.onShowDialogSort(sortId)
    }

    override fun sortMobileData(sortId: Int) {
        this@MobileListPresenterImpl.view?.onSortMobileData(sortId)
    }

    override fun addFavorite(data: MobileData) {
        this@MobileListPresenterImpl.interactor.addFavoriteToDatabase(this@MobileListPresenterImpl, data)
    }

    override fun onAddFavoriteSuccess() {
        this@MobileListPresenterImpl.view?.onAddFavoriteSuccess()
    }
}