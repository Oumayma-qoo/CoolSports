package com.example.coolsports.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coolsports.domain.mapper.DomainModel
import com.example.coolsports.domain.mapper.LocalModel
import com.example.coolsports.domain.mapper.NetworkModel
import com.google.gson.annotations.SerializedName


@Entity(tableName = "teamInfo")
data class TeamInfoDto (
    @PrimaryKey   var teamId: Int? = null,
     var leagueId: Int? = null,
     var nameEn: String? = null,
     var nameChs: String? = null,
     var nameCht: String? = null,
     var foundingDate: String? = null,
     var areaEn: String? = null,
     var areaCn: String? = null,
     var gymEn: String? = null,
     var gymCn: String? = null,
     var capacity: String? = null,
     var logo: String? = null,
     var addrEn: String? = null,
     var addrCn: String? = null,
     var website: String? = null,
     var coachEn: String? = null,
     var nameId: String? = null,
     var areaId: String? = null,
     var gymId: String? = null,
     var coachId: String? = null,
     var nameVi: String? = null,
     var areaVi: String? = null,
     var gymVi: String? = null,
     var coachVi: String? = null,
     var nameTh: String? = null,
     var areaTh: String? = null,
     var gymTh: String? = null,
     var coachTh: String? = null,
     var nameCn: String? = null,
): DomainModel



@Entity(tableName = "teamPlayerInfo")
data class TeamPlayerDto (
    @PrimaryKey   var id: Int? = null,
     var playerId: Int? = null,
     var nameEn: String? = null,
     var nameChs: String? = null,
     var nameCht: String? = null,
     var birthday: String? = null,
     var height: String? = null,
     var weight: String? = null,
     var countryEn: String? = null,
     var countryCn: String? = null,
     var countryId: String? = null,
     var photo: String? = null,
     var value: String? = null,
     var feetEn: String? = null,
     var feetCn: String? = null,
     var introduceEn: String? = null,
     var introduceCn: String? = null,
     var teamID: Int? = null,
     var positionEn: String? = null,
     var positionCn: String? = null,
     var number: String? = null,
     var endDateContract: String? = null,
     var nameCn: String? = null,
): DomainModel


@Entity(tableName = "players")
data class PlayerDto (
    @PrimaryKey var playerId: Int? = null,
    var playerNameEn: String? = null,
    var playerNameChs: String? = null,
    var playerNameCht: String? = null,
    var countryEn: String? = null,
    var countryCn: String? = null,
    var teamID: Int? = null,
    var teamNameEn: String? = null,
    var teamNameChs: String? = null,
    var teamNameCht: String? = null,
    var goals: Int? = null,
    var homeGoals: Int? = null,
    var awayGoals: Int? = null,
    var homePenalty: Int? = null,
    var awayPenalty: Int? = null,

    ): DomainModel