package com.sittipong.scbtestandroid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class MobileData() : RealmModel, Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("rating")
    @Expose
    var rating: Double? = null
    @SerializedName("thumbImageURL")
    @Expose
    var thumbImageURL: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("brand")
    @Expose
    var brand: String? = null

    var isFavorite :Boolean? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        rating = parcel.readValue(Double::class.java.classLoader) as? Double
        thumbImageURL = parcel.readString()
        description = parcel.readString()
        price = parcel.readValue(Double::class.java.classLoader) as? Double
        brand = parcel.readString()
        isFavorite = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeValue(rating)
        parcel.writeString(thumbImageURL)
        parcel.writeString(description)
        parcel.writeValue(price)
        parcel.writeString(brand)
        parcel.writeValue(isFavorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MobileData> {
        override fun createFromParcel(parcel: Parcel): MobileData {
            return MobileData(parcel)
        }

        override fun newArray(size: Int): Array<MobileData?> {
            return arrayOfNulls(size)
        }
    }
}