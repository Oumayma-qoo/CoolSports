package com.cool.sports.ranking.domain.mapper

import com.cool.sports.ranking.data.local.TeamPlayerDto
import com.cool.sports.ranking.domain.model.team.TeamPlayer
import javax.inject.Inject

class NetworkMapperPlayer@Inject constructor() : EntityMapper<TeamPlayer, TeamPlayerDto> {
    override fun mapToDomain(apiEntity: TeamPlayer): TeamPlayerDto {
        return TeamPlayerDto(
            id= apiEntity.id,
            playerId= apiEntity.playerId,
            nameEn= apiEntity.nameEn,
            nameChs= apiEntity.nameChs,
            nameCht= apiEntity.nameCht,
            birthday= apiEntity.birthday,
            height= apiEntity.height,
            weight= apiEntity.weight,
            countryEn= apiEntity.countryEn,
            countryCn= apiEntity.countryCn,
            countryId= apiEntity.countryId,
            photo= apiEntity.photo,
            value= apiEntity.value,
            feetEn= apiEntity.feetEn,
            feetCn= apiEntity.feetCn,
            introduceEn= apiEntity.introduceEn,
            introduceCn= apiEntity.introduceCn,
            teamID= apiEntity.teamID,
            positionEn= apiEntity.positionEn,
            positionCn= apiEntity.positionCn,
            number= apiEntity.number,
            endDateContract= apiEntity.endDateContract,
            nameCn= apiEntity.nameCn
        )
    }

    override fun mapFromDomain(domainModel: TeamPlayerDto): TeamPlayer {
        return TeamPlayer(
            id= domainModel.id,
            playerId= domainModel.playerId,
            nameEn= domainModel.nameEn,
            nameChs= domainModel.nameChs,
            nameCht= domainModel.nameCht,
            birthday= domainModel.birthday,
            height= domainModel.height,
            weight= domainModel.weight,
            countryEn= domainModel.countryEn,
            countryCn= domainModel.countryCn,
            countryId= domainModel.countryId,
            photo= domainModel.photo,
            value= domainModel.value,
            feetEn= domainModel.feetEn,
            feetCn= domainModel.feetCn,
            introduceEn= domainModel.introduceEn,
            introduceCn= domainModel.introduceCn,
            teamID= domainModel.teamID,
            positionEn= domainModel.positionEn,
            positionCn= domainModel.positionCn,
            number= domainModel.number,
            endDateContract= domainModel.endDateContract,
            nameCn= domainModel.nameCn
        )

    }

}