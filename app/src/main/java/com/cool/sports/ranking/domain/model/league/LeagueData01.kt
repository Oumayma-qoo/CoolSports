package com.cool.sports.ranking.domain.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueData01(
    @SerializedName("areaId")
    var areaId: String = "",
    @SerializedName("color")
    var color: String = "",
    @SerializedName("countryCn")
    var countryCn: String = "",
    @SerializedName("countryEn")
    var countryEn: String = "",
    @SerializedName("countryId")
    var countryId: String = "",
    @SerializedName("countryLogo")
    var countryLogo: String = "",
    @SerializedName("countryNameCn")
    var countryNameCn: String = "",
    @SerializedName("countryNameEn")
    var countryNameEn: String = "",
    @SerializedName("countryNameId")
    var countryNameId: String = "",
    @SerializedName("countryNameTh")
    var countryNameTh: String = "",
    @SerializedName("countryNameVi")
    var countryNameVi: String = "",
    @SerializedName("currRound")
    var currRound: String = "",
    @SerializedName("currSeason")
    var currSeason: String = "",
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("leagueLogo")
    var leagueLogo: String = "",
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameChsShort")
    var nameChsShort: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameChtShort")
    var nameChtShort: String = "",
    @SerializedName("nameCn")
    var nameCn: String = "",
    @SerializedName("nameCnShort")
    var nameCnShort: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("nameEnShort")
    var nameEnShort: String = "",
    @SerializedName("nameId")
    var nameId: String = "",
    @SerializedName("nameIdShort")
    var nameIdShort: String = "",
    @SerializedName("nameTh")
    var nameTh: String = "",
    @SerializedName("nameThShort")
    var nameThShort: String = "",
    @SerializedName("nameVi")
    var nameVi: String = "",
    @SerializedName("nameViShort")
    var nameViShort: String = "",
    @SerializedName("subSclassCn")
    var subSclassCn: String = "",
    @SerializedName("subSclassEn")
    var subSclassEn: String = "",
    @SerializedName("subSclassId")
    var subSclassId: String = "",
    @SerializedName("subSclassTh")
    var subSclassTh: String = "",
    @SerializedName("subSclassVi")
    var subSclassVi: String = "",
    @SerializedName("sumRound")
    var sumRound: String = "",
    @SerializedName("type")
    var type: String = ""
):Parcelable