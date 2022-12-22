package com.cool.sports.ranking.domain.mapper

import com.cool.sports.ranking.data.local.PlayerDto
import com.cool.sports.ranking.domain.model.player.Player
import javax.inject.Inject

class NetworkMapperPlayerStandings @Inject constructor() : EntityMapper<Player, PlayerDto> {
    override fun mapToDomain(apiEntity: Player): PlayerDto {
        return PlayerDto(
            playerId= apiEntity.playerId,
            playerNameEn= apiEntity.playerNameEn,
            playerNameChs= apiEntity.playerNameChs,
            playerNameCht= apiEntity.playerNameCht,
            countryEn= apiEntity.countryEn,
            countryCn= apiEntity.countryCn,
            teamID= apiEntity.teamID,
            teamNameEn= apiEntity.teamNameEn,
            teamNameChs= apiEntity.teamNameChs,
            teamNameCht= apiEntity.teamNameCht,
            goals= apiEntity.goals,
            homeGoals= apiEntity.homeGoals,
            awayGoals= apiEntity.awayGoals,
            homePenalty= apiEntity.homePenalty,
            awayPenalty= apiEntity.awayPenalty
        )
    }

    override fun mapFromDomain(domainModel: PlayerDto): Player {
        return Player(
            playerId= domainModel.playerId,
            playerNameEn= domainModel.playerNameEn,
            playerNameChs= domainModel.playerNameChs,
            playerNameCht= domainModel.playerNameCht,
            countryEn= domainModel.countryEn,
            countryCn= domainModel.countryCn,
            teamID= domainModel.teamID,
            teamNameEn= domainModel.teamNameEn,
            teamNameChs= domainModel.teamNameChs,
            teamNameCht= domainModel.teamNameCht,
            goals= domainModel.goals,
            homeGoals= domainModel.homeGoals,
            awayGoals= domainModel.awayGoals,
            homePenalty= domainModel.homePenalty,
            awayPenalty= domainModel.awayPenalty
        )
    }


}