package com.cool.sports.ranking.domain.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class LeagueData04(
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("ruleCn")
    var ruleCn: String = "",
    @SerializedName("ruleEn")
    var ruleEn: String = "",
    @SerializedName("ruleId")
    var ruleId: String = "",
    @SerializedName("ruleTh")
    var ruleTh: String = "",
    @SerializedName("ruleVi")
    var ruleVi: String = ""
):Parcelable