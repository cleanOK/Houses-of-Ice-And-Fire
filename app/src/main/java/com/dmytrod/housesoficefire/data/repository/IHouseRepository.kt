package com.dmytrod.housesoficefire.data.repository

import com.dmytrod.housesoficefire.domain.entity.HouseEntity

interface IHouseRepository {
    fun getAll(): List<HouseEntity>
}