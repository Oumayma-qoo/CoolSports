package com.example.coolsports.domain.model.team

import com.example.coolsports.domain.mapper.NetworkModel
import com.google.gson.annotations.SerializedName

data class TeamPlayer (
    @SerializedName("id")
     var id: Int? = null,
    @SerializedName("playerId")
     var playerId: Int? = null,
    @SerializedName("nameEn")
     var nameEn: String? = null,
    @SerializedName("nameChs")
     var nameChs: String? = null,
    @SerializedName("nameCht")
     var nameCht: String? = null,
    @SerializedName("birthday")
     var birthday: String? = null,
    @SerializedName("height")
     var height: String? = null,
    @SerializedName("weight")
     var weight: String? = null,
    @SerializedName("countryEn")
     var countryEn: String? = null,
    @SerializedName("countryCn")
     var countryCn: String? = null,
    @SerializedName("countryId")
     var countryId: String? = null,
    @SerializedName("photo")
     var photo: String? = null,
    @SerializedName("value")
     var value: String? = null,
    @SerializedName("feetEn")
     var feetEn: String? = null,
    @SerializedName("feetCn")
     var feetCn: String? = null,
    @SerializedName("introduceEn")
     var introduceEn: String? = null,
    @SerializedName("introduceCn")
     var introduceCn: String? = null,
    @SerializedName("teamID")
     var teamID: Int? = null,
    @SerializedName("positionEn")
     var positionEn: String? = null,
    @SerializedName("positionCn")
     var positionCn: String? = null,
    @SerializedName("number")
     var number: String? = null,
    @SerializedName("endDateContract")
     var endDateContract: String? = null,
    @SerializedName("nameCn")
     var nameCn: String? = null,
        ): NetworkModel


