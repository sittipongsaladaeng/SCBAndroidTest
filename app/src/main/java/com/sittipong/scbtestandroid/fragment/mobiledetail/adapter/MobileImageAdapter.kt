package com.sittipong.scbtestandroid.fragment.mobiledetail.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.model.ImageMobileData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mobile_image.view.*


class MobileImageAdapter :
    RecyclerView.Adapter<MobileImageAdapter.ViewHolder>() {

    var images: MutableList<ImageMobileData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mobile_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun setData(i: MutableList<ImageMobileData>) {
        images = i
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: ImageMobileData) {
            Picasso.with(itemView.context).load(item.url).into(itemView.mobileImage)
        }
    }
}
