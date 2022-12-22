package com.cool.sports.ranking.domain.mapper

interface Entity
interface NetworkModel: Entity
interface LocalModel: Entity
interface DomainModel

interface EntityMapper <Entity, DomainModel>
        where Entity: com.cool.sports.ranking.domain.mapper.Entity,
              DomainModel: com.cool.sports.ranking.domain.mapper.DomainModel {
    fun mapToDomain(apiEntity: Entity): DomainModel
    fun mapFromDomain(domainModel: DomainModel): Entity
}