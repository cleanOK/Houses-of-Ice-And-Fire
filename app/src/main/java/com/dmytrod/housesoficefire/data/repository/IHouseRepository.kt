package com.dmytrod.housesoficefire.data.repository

import com.dmytrod.housesoficefire.data.db.model.HouseDBModel
import com.dmytrod.housesoficefire.data.remote.model.HouseResponse
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import kotlinx.coroutines.flow.Flow

interface IHouseRepository {
    suspend fun fetchAllFromRemote(): IResponseHandler.Response<List<HouseResponse>>
    suspend fun loadAllFromDB() : Flow<List<HouseDBModel>>
    suspend fun saveToDB(data: List<HouseResponse>)
}