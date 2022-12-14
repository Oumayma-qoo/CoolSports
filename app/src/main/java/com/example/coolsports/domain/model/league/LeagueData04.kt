package com.example.coolsports.domain.model.league

import com.google.gson.annotations.SerializedName

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
)