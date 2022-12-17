package com.example.coolsports.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueInfo(
    @SerializedName("color")
    var color: String = "",
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("logo")
    var logo: String = "",
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameChsShort")
    var nameChsShort: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameChtShort")
    var nameChtShort: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("nameEnShort")
    var nameEnShort: String = "",
    @SerializedName("season")
    var season: String = ""
):Parcelable