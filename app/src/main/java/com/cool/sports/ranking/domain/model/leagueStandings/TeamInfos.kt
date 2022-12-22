package com.cool.sports.ranking.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamInfos(
    @SerializedName("conferenceFlg")
    var conferenceFlg: Int = 0,
    @SerializedName("flag")
    var flag: String = "",
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameCn")
    var nameCn: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("teamId")
    var teamId: Int = 0
):Parcelable