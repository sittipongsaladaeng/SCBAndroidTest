package com.sittipong.scbtestandroid.fragment.mobilelist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.activity.main.OnRefreshContent
import com.sittipong.scbtestandroid.activity.mobiledetail.MobileDetailActivity
import com.sittipong.scbtestandroid.base.BaseFragment
import com.sittipong.scbtestandroid.fragment.mobiledetail.views.ARG_MOBILE_DATA
import com.sittipong.scbtestandroid.fragment.mobilefavorite.adapter.FavoriteAdapter
import com.sittipong.scbtestandroid.fragment.mobilefavorite.presenters.MobileFavoritePresenter
import com.sittipong.scbtestandroid.fragment.mobilefavorite.presenters.MobileFavoritePresenterImpl
import com.sittipong.scbtestandroid.fragment.mobilefavorite.views.MobileFavoriteView
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.DELETE_FAVORITE
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.READ
import com.sittipong.scbtestandroid.model.MobileData
import kotlinx.android.synthetic.main.fragment_mobile_list_favorite.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MobileFavoriteFragment : BaseFragment(), MobileFavoriteView {

    companion object {
        fun newInstance(callback: OnRefreshContent?): MobileFavoriteFragment {
            val fragment = MobileFavoriteFragment()
            fragment.onRefreshCallback = callback
            fragment.arguments = Bundle()
            return fragment
        }
    }

    private var onRefreshCallback: OnRefreshContent? = null
    var sortId: Int = -1
    var adapter: FavoriteAdapter? = null
    var presenter: MobileFavoritePresenter = MobileFavoritePresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoriteAdapter(onClick)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_mobile_list_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.bindView(this)
        presenter?.queryMobileList()

        with(listMobileFavorite) {
            adapter = this@MobileFavoriteFragment.adapter
            layoutManager = LinearLayoutManager(view.context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private val onClick: (actionId: Int, data: MobileData) -> Unit = { actionId, data ->
        when (actionId) {
            READ -> presenter?.nextToMobileDetailPage(data)
            DELETE_FAVORITE -> presenter?.delete(data)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.unBind()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: String) {
        if (event.equals(FAVORITE))
            presenter.showDialogSort(this@MobileFavoriteFragment.sortId)
        else if (event.equals(REFRESH))
            presenter.queryMobileList()
    }


    override fun onQuerySuccess(datas: MutableList<MobileData>) {
        txtEmpty.visibility = View.GONE
        adapter?.setData(datas)
    }

    override fun onEmpty() {
        txtEmpty.visibility = View.VISIBLE
        adapter?.clearData()
    }

    override fun onNextToMobileDetailPage(data: MobileData) {
        var intent = Intent(context, MobileDetailActivity::class.java)
        intent.putExtra(ARG_MOBILE_DATA, data)
        startActivity(intent)
    }

    override fun onDeleteSuccess() {
        presenter.queryMobileList()
        onRefreshCallback?.refresh()
    }

    override fun onShowDialogSort(sortId: Int) {
        val dialog = AlertDialog.Builder(activity!!)
            .setSingleChoiceItems(items, sortId) { dialog, which ->
                this@MobileFavoriteFragment.sortId = which
                presenter?.sortMobileData(this@MobileFavoriteFragment.sortId)
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

    override fun onSortMobileData(sortId: Int) {
        adapter?.sortData(sortId)
    }
}