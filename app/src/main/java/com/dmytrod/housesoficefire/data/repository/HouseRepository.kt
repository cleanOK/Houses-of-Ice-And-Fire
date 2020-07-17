package com.dmytrod.housesoficefire.data.repository

import IResponseHandler
import com.dmytrod.housesoficefire.data.db.IceAndFireDatabase
import com.dmytrod.housesoficefire.data.remote.IceAndFireApi
import com.dmytrod.housesoficefire.domain.entity.HouseEntity

class HouseRepository(
    private val iceAndFireApi: IceAndFireApi,
    private val iceAndFireDatabase: IceAndFireDatabase,
    private val responseHandler: IResponseHandler
) : IHouseRepository {
    override fun getAll(): List<HouseEntity> {
        TODO("Not yet implemented")
    }
}