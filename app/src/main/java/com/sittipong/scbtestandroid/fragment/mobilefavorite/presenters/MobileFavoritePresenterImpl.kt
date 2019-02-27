package com.sittipong.scbtestandroid.fragment.mobilefavorite.presenters

import com.sittipong.scbtestandroid.fragment.mobilefavorite.interactors.MobileFavoriteInteractor
import com.sittipong.scbtestandroid.fragment.mobilefavorite.interactors.MobileFavoriteInteractorImpl
import com.sittipong.scbtestandroid.fragment.mobilefavorite.interfaces.OnQuerySuccess
import com.sittipong.scbtestandroid.fragment.mobilefavorite.views.MobileFavoriteView
import com.sittipong.scbtestandroid.model.MobileData

class MobileFavoritePresenterImpl : MobileFavoritePresenter, OnQuerySuccess {

    var view: MobileFavoriteView? = null
    var interactor: MobileFavoriteInteractor = MobileFavoriteInteractorImpl()

    override fun bindView(view: MobileFavoriteView) {
        this@MobileFavoritePresenterImpl.view = view
    }

    override fun unBind() {
        this@MobileFavoritePresenterImpl.view = null
    }

    override fun queryMobileList() {
        this@MobileFavoritePresenterImpl.interactor.queryMobileFavorite(this)
    }

    override fun onQuerySuccess(datas: MutableList<MobileData>) {
        this@MobileFavoritePresenterImpl.view?.onQuerySuccess(datas)
    }

    override fun onDeleteSuccess() {
        this@MobileFavoritePresenterImpl.view?.onDeleteSuccess()
    }

    override fun onEmpty() {
        this@MobileFavoritePresenterImpl.view?.onEmpty()
    }

    override fun delete(data: MobileData) {
        this@MobileFavoritePresenterImpl.interactor.deleteFavorite(data, this)
    }

    override fun nextToMobileDetailPage(data: MobileData) {
        this@MobileFavoritePresenterImpl.view?.onNextToMobileDetailPage(data)
    }

    override fun showDialogSort(sortId: Int) {
        this@MobileFavoritePresenterImpl.view?.onShowDialogSort(sortId)
    }

    override fun sortMobileData(sortId: Int) {
        this@MobileFavoritePresenterImpl.view?.onSortMobileData(sortId)
    }
}