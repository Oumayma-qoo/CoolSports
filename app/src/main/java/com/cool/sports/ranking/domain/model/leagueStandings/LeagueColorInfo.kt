package com.cool.sports.ranking.domain.model.leagueStandings

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueColorInfo(
    @SerializedName("beginRank")
    var beginRank: Int = 0,
    @SerializedName("color")
    var color: String = "",
    @SerializedName("endRank")
    var endRank: Int = 0,
    @SerializedName("leagueNameChs")
    var leagueNameChs: String = "",
    @SerializedName("leagueNameCht")
    var leagueNameCht: String = "",
    @SerializedName("leagueNameEn")
    var leagueNameEn: String = ""
):Parcelable