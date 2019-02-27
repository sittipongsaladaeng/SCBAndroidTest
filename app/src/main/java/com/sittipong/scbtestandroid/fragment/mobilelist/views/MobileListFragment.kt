package com.sittipong.scbtestandroid.fragment.mobilelist.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.activity.main.OnRefreshContent
import com.sittipong.scbtestandroid.activity.mobiledetail.MobileDetailActivity
import com.sittipong.scbtestandroid.base.BaseFragment
import com.sittipong.scbtestandroid.fragment.mobiledetail.views.ARG_MOBILE_DATA
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.ADD_FAVORITE
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.MobileListAdapter
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.READ
import com.sittipong.scbtestandroid.fragment.mobilelist.presenters.MobileListPresenter
import com.sittipong.scbtestandroid.fragment.mobilelist.presenters.MobileListPresenterImpl
import com.sittipong.scbtestandroid.model.MobileData
import kotlinx.android.synthetic.main.fragment_mobile_list.*
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.EventBus


class MobileListFragment : BaseFragment(), MobileView {

    companion object {
        fun newInstance(callback: OnRefreshContent): MobileListFragment {
            val fragment = MobileListFragment()
            fragment.onRefreshCallback = callback
            fragment.arguments = Bundle()
            return fragment
        }
    }

    private var onRefreshCallback: OnRefreshContent? = null
    var presenter: MobileListPresenter = MobileListPresenterImpl()
    var adapter: MobileListAdapter? = null
    var sortId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter?.bindView(this@MobileListFragment)
        presenter?.callServiceMobileList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_mobile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MobileListAdapter(onClick)
        with(listMobile) {
            adapter = this@MobileListFragment.adapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this@MobileListFragment)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this@MobileListFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.unBind()
    }

    private val onClick: (actionId: Int, data: MobileData) -> Unit = { actionId, data ->
        when (actionId) {
            READ -> presenter?.nextToMobileDetailPage(data)
            ADD_FAVORITE -> presenter?.addFavorite(data)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: String) {
        if (event.equals(LIST))
            presenter?.showDialogSort(this@MobileListFragment.sortId)
        else if (event.equals(REFRESH))
            adapter?.notifyDataSetChanged()
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun onLoadMobileDataSuccess(datas: MutableList<MobileData>) {
        adapter?.setData(datas!!)
    }

    override fun onLoadMobileDataFailed(code: Int, msg: String) {
        showAlertDialog(activity as AppCompatActivity, msg)
    }

    override fun onSortMobileData(sortId: Int) {
        adapter?.sortData(sortId)
    }

    override fun onNextToMobileDetailPage(data: MobileData) {
        var intent = Intent(context, MobileDetailActivity::class.java)
        intent.putExtra(ARG_MOBILE_DATA, data)
        startActivity(intent)
    }

    override fun onAddFavoriteSuccess() {
        adapter?.notifyDataSetChanged()
        onRefreshCallback?.refresh()
    }

    override fun onShowDialogSort(sortId: Int) {
        val dialog = AlertDialog.Builder(activity!!)
            .setSingleChoiceItems(items, sortId) { dialog, which ->
                this@MobileListFragment.sortId = which
                presenter?.sortMobileData(this@MobileListFragment.sortId)
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }
}