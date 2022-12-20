package com.example.coolsports.domain.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Map : Parcelable {
    var map_key: String?
    var map_link: String?
    var open_type = 0

    constructor(map_key: String?, map_link: String?, open_type: Int) {
        this.map_key = map_key
        this.map_link = map_link
        this.open_type = open_type
    }

    protected constructor(`in`: Parcel) {
        map_key = `in`.readString()
        map_link = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(map_key)
        dest.writeString(map_link)
    }



    companion object CREATOR : Creator<Map> {
        override fun createFromParcel(parcel: Parcel): Map {
            return Map(parcel)
        }

        override fun newArray(size: Int): Array<Map?> {
            return arrayOfNulls(size)
        }
    }
}
