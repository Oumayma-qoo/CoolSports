package com.example.coolsports.domain.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Ads : Parcelable {
    var image_path: String?
    var redirect_url: String?
    var open_type: String?

    constructor(image_path: String?, redirect_url: String?, open_type: String?) {
        this.image_path = image_path
        this.redirect_url = redirect_url
        this.open_type = open_type
    }

    protected constructor(`in`: Parcel) {
        image_path = `in`.readString()
        redirect_url = `in`.readString()
        open_type = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(image_path)
        dest.writeString(redirect_url)
        dest.writeString(open_type)
    }


    companion object CREATOR : Creator<Ads> {
        override fun createFromParcel(parcel: Parcel): Ads {
            return Ads(parcel)
        }

        override fun newArray(size: Int): Array<Ads?> {
            return arrayOfNulls(size)
        }
    }
}