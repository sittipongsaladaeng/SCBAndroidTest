package com.sittipong.scbtestandroid.fragment.mobilelist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sittipong.scbtestandroid.R
import com.sittipong.scbtestandroid.model.MobileData
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.item_mobile.view.*


const val READ = 1
const val ADD_FAVORITE = 2
const val DELETE_FAVORITE = 3

class MobileListAdapter(private val callback: (actionId: Int, data: MobileData) -> Unit) :
    RecyclerView.Adapter<MobileListAdapter.ViewHolder>() {

    var dataList: MutableList<MobileData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mobile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dataList[position], callback)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(datas: MutableList<MobileData>) {
        dataList = datas
        notifyDataSetChanged()
    }

    fun sortData(sortId: Int) {
        when (sortId) {
            0 -> dataList = dataList.sortedWith(compareBy({ it.price })).toMutableList()
            1 -> dataList = dataList.sortedWith(compareByDescending({ it.price })).toMutableList()
            2 -> dataList = dataList.sortedWith(compareByDescending({ it.rating })).toMutableList()
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: MobileData, callback: (actionId: Int, data: MobileData) -> Unit) {
            Picasso.with(itemView.context).load(item.thumbImageURL).into(itemView.image)
            itemView.txtName.text = item.name
            itemView.txtDesc.text = item.description
            itemView.txtPrice.text = "Price: $${item.price}"
            itemView.txtRating.text = "Rating: ${item.rating}"

            val favItem = Realm.getDefaultInstance().where(MobileData::class.java).equalTo("id", item.id).findFirst()
            if (favItem == null) itemView.imageFavorite.setImageResource(R.mipmap.ic_unfavorite)
            else itemView.imageFavorite.setImageResource(R.mipmap.ic_favorite)

            itemView.setOnClickListener {
                callback.invoke(READ, item)
            }

            itemView.imageFavorite.setOnClickListener {
                callback(ADD_FAVORITE, item)
            }
        }
    }
}
