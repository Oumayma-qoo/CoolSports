package com.example.coolsports.domain.model.leagueStandings.LeagueStandingGroup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GroupScore(
    @SerializedName("groupCn")
    var groupCn: String = "",
    @SerializedName("groupEn")
    var groupEn: String = "",
    @SerializedName("scoreItems")
    var scoreItems: List<ScoreItem> = listOf()
):Parcelable