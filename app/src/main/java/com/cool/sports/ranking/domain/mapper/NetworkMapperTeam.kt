package com.cool.sports.ranking.domain.mapper

import com.cool.sports.ranking.data.local.TeamInfoDto
import com.cool.sports.ranking.domain.model.team.TeamInfo
import javax.inject.Inject

class NetworkMapperTeam @Inject constructor() : EntityMapper<TeamInfo, TeamInfoDto> {
    override fun mapToDomain(apiEntity: TeamInfo): TeamInfoDto {
        return TeamInfoDto(
            teamId = apiEntity.teamId,
            leagueId = apiEntity.leagueId,
            nameEn = apiEntity.nameEn,
            nameChs = apiEntity.nameChs,
            foundingDate = apiEntity.foundingDate,
            areaEn = apiEntity.areaEn,
            areaCn = apiEntity.areaCn,
            gymEn = apiEntity.gymEn,
            gymCn = apiEntity.gymCn,
            capacity = apiEntity.capacity,
            logo = apiEntity.logo,
            addrEn = apiEntity.addrEn,
            addrCn = apiEntity.addrCn,
            website = apiEntity.website,
            coachEn = apiEntity.coachEn,
            nameId = apiEntity.nameId,
            areaId = apiEntity.areaId,
            gymId = apiEntity.gymId,
            coachId = apiEntity.coachId,
            nameVi = apiEntity.nameVi,
            areaVi = apiEntity.areaVi,
            gymVi = apiEntity.gymVi,
            coachVi = apiEntity.coachVi,
            nameTh = apiEntity.nameTh,
            areaTh = apiEntity.areaTh,
            gymTh = apiEntity.gymTh,
            coachTh = apiEntity.coachTh,
            nameCn = apiEntity.nameCn
        )


    }

    override fun mapFromDomain(domainModel: TeamInfoDto): TeamInfo {
        return TeamInfo(
            teamId = domainModel.teamId,
            leagueId = domainModel.leagueId,
            nameEn = domainModel.nameEn,
            nameChs = domainModel.nameChs,
            foundingDate = domainModel.foundingDate,
            areaEn = domainModel.areaEn,
            areaCn = domainModel.areaCn,
            gymEn = domainModel.gymEn,
            gymCn = domainModel.gymCn,
            capacity = domainModel.capacity,
            logo = domainModel.logo,
            addrEn = domainModel.addrEn,
            addrCn = domainModel.addrCn,
            website = domainModel.website,
            coachEn = domainModel.coachEn,
            nameId = domainModel.nameId,
            areaId = domainModel.areaId,
            gymId = domainModel.gymId,
            coachId = domainModel.coachId,
            nameVi = domainModel.nameVi,
            areaVi = domainModel.areaVi,
            gymVi = domainModel.gymVi,
            coachVi = domainModel.coachVi,
            nameTh = domainModel.nameTh,
            areaTh = domainModel.areaTh,
            gymTh = domainModel.gymTh,
            coachTh = domainModel.coachTh,
            nameCn = domainModel.nameCn

        )
    }
}