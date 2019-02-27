package com.sittipong.scbtestandroid.fragment.mobiledetail.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.base.BaseFragment
import com.sittipong.scbtestandroid.fragment.mobiledetail.adapter.MobileImageAdapter
import com.sittipong.scbtestandroid.fragment.mobiledetail.presenters.MobileDetailPresenter
import com.sittipong.scbtestandroid.fragment.mobiledetail.presenters.MobileDetailPresenterImpl
import com.sittipong.scbtestandroid.model.ImageMobileData
import com.sittipong.scbtestandroid.model.MobileData
import kotlinx.android.synthetic.main.fragment_mobile_detail.*

const val ARG_MOBILE_DATA = "mobiledata"

class MobileDetailFragment : BaseFragment(), MobileDetailView {

    companion object {
        @JvmStatic
        fun newInstance(mobileData: MobileData?) = MobileDetailFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(ARG_MOBILE_DATA, mobileData)
            }
        }
    }

    var presenter: MobileDetailPresenter = MobileDetailPresenterImpl()
    var mobileData: MobileData? = null
    var adapter: MobileImageAdapter = MobileImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this@MobileDetailFragment)
        arguments?.let {
            mobileData = it.getParcelable(ARG_MOBILE_DATA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_mobile_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setTextData(mobileData!!)
        presenter.callServiceImageMobile(mobileData!!.id!!)
        with(listMobileImage) {
            adapter = this@MobileDetailFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unBind()
    }


    override fun showProgress() {
        progressBarImage.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progressBarImage.visibility = View.GONE
    }

    override fun onLoadImageMobileSuccess(images: MutableList<ImageMobileData>) {
        adapter?.setData(images)
    }

    override fun onLoadImageMobileFailed(code: Int, msg: String) {
        showAlertDialog(activity as AppCompatActivity, msg)
    }

    override fun onSetTextData(data: MobileData) {
        txtName.text = data.name
        txtBrand.text = data.brand
        txtDesc.text = data.description
        txtRating.text = "Rating: ${data.rating}"
        txtPrice.text = "Price: $${data.price}"
    }
}