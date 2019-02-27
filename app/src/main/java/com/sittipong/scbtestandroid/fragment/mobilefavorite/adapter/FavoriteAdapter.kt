package com.sittipong.scbtestandroid.fragment.mobilefavorite.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.DELETE_FAVORITE
import com.sittipong.scbtestandroid.fragment.mobilelist.adapter.READ
import com.sittipong.scbtestandroid.model.MobileData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_delete.view.*
import kotlinx.android.synthetic.main.item_favorite_font.view.*

class FavoriteAdapter(private val callback: (actionId: Int, data: MobileData) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var datas: MutableList<MobileData>? = null

    fun setData(datas: MutableList<MobileData>) {
        this@FavoriteAdapter.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindView(datas!![position], callback)
    }

    override fun getItemCount(): Int {
        if (datas == null) return 0
        return datas!!.size
    }

    fun clearData() {
        datas?.clear()
        notifyDataSetChanged()
    }

    fun sortData(sortId: Int) {
        if (datas == null) return
        when (sortId) {
            0 -> datas = datas?.sortedWith(compareBy({ it.price }))!!.toMutableList()
            1 -> datas = datas?.sortedWith(compareByDescending({ it.price }))!!.toMutableList()
            2 -> datas = datas?.sortedWith(compareByDescending({ it.rating }))!!.toMutableList()
        }
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: MobileData, callback: (actionId: Int, data: MobileData) -> Unit) {
            Picasso.with(itemView.context).load(item.thumbImageURL).into(itemView.image)
            itemView.txtName.text = item.name
            itemView.txtPrice.text = "Price: $${item.price}"
            itemView.txtRating.text = "Rating: ${item.rating}"

            itemView.setOnClickListener {
                callback.invoke(READ, item)
            }

            itemView.btnDelete.setOnClickListener {
                callback.invoke(DELETE_FAVORITE, item)
            }
        }
    }
}


