package com.sittipong.scbtestandroid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ImageMobileData() : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("mobile_id")
    @Expose
    var mobile_id: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        mobile_id = parcel.readValue(Int::class.java.classLoader) as? Int
        url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(mobile_id)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageMobileData> {
        override fun createFromParcel(parcel: Parcel): ImageMobileData {
            return ImageMobileData(parcel)
        }

        override fun newArray(size: Int): Array<ImageMobileData?> {
            return arrayOfNulls(size)
        }
    }
}