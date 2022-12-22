package com.cool.sports.ranking.domain.model.team

import com.cool.sports.ranking.domain.mapper.NetworkModel
import com.google.gson.annotations.SerializedName

data class TeamInfo (
    @SerializedName("teamId")
    var teamId:Int?=null,
    @SerializedName("leagueId")
     var leagueId: Int? = null,
    @SerializedName("nameEn")
     var nameEn: String? = null,
    @SerializedName("nameChs")
     var nameChs: String? = null,
    @SerializedName("nameCht")
     var nameCht: String? = null,
    @SerializedName("foundingDate")
     var foundingDate: String? = null,
    @SerializedName("areaEn")
     var areaEn: String? = null,
    @SerializedName("areaCn")
     var areaCn: String? = null,
    @SerializedName("gymEn")
     var gymEn: String? = null,
    @SerializedName("gymCn")
     var gymCn: String? = null,
    @SerializedName("capacity")
     var capacity: String? = null,
    @SerializedName("logo")
     var logo: String? = null,
    @SerializedName("addrEn")
     var addrEn: String? = null,
    @SerializedName("addrCn")
     var addrCn: String? = null,
    @SerializedName("website")
     var website: String? = null,
    @SerializedName("coachEn")
     var coachEn: String? = null,
    @SerializedName("nameId")
     var nameId: String? = null,
    @SerializedName("areaId")
     var areaId: String? = null,
    @SerializedName("gymId")
     var gymId: String? = null,
    @SerializedName("coachId")
     var coachId: String? = null,
    @SerializedName("nameVi")
     var nameVi: String? = null,
    @SerializedName("areaVi")
     var areaVi: String? = null,
   @SerializedName("gymVi")
     var gymVi: String? = null,
   @SerializedName("coachVi")
     var coachVi: String? = null,
   @SerializedName("nameTh")
     var nameTh: String? = null,
   @SerializedName("areaTh")
     var areaTh: String? = null,
   @SerializedName("gymTh")
     var gymTh: String? = null,
   @SerializedName("coachTh")
     var coachTh: String? = null,
    @SerializedName("coachCn")
     var coachCn: String? = null,
   @SerializedName("nameCn")
     var nameCn: String? = null,
): NetworkModel