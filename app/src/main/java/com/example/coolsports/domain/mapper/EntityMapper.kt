package com.example.coolsports.domain.mapper

interface Entity
interface NetworkModel: Entity
interface LocalModel: Entity
interface DomainModel

interface EntityMapper <Entity, DomainModel>
        where Entity: com.example.coolsports.domain.mapper.Entity,
              DomainModel: com.example.coolsports.domain.mapper.DomainModel {
    fun mapToDomain(apiEntity: Entity): DomainModel
    fun mapFromDomain(domainModel: DomainModel): Entity
}