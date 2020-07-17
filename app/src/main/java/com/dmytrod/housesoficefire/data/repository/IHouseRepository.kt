package com.dmytrod.housesoficefire.data.repository

import com.dmytrod.housesoficefire.data.db.model.HouseDBModel
import com.dmytrod.housesoficefire.data.remote.model.HouseResponse
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import kotlinx.coroutines.flow.Flow

interface IHouseRepository {
    fun getAll(): List<HouseEntity>
    fun fetchAllFromRemote(): IResponseHandler.Response<List<HouseResponse>> {
        TODO("Not yet implemented")
    }

    fun loadAllFromDB() : Flow<List<HouseDBModel>>
    fun saveToDB(data: List<HouseResponse>)
}