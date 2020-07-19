package com.dmytrod.housesoficefire.data.repository

import com.dmytrod.housesoficefire.data.db.model.HouseDBModel
import com.dmytrod.housesoficefire.data.remote.IResponseHandler
import com.dmytrod.housesoficefire.data.remote.model.HouseResponse
import kotlinx.coroutines.flow.Flow

interface IHouseRepository {
    suspend fun fetchAllFromRemote(): IResponseHandler.Response<List<HouseResponse>>
    fun loadAllFromDB() : Flow<List<HouseDBModel>>
    suspend fun saveToDB(data: List<HouseResponse>)
    fun loadFromDBByUrl(param: String): Flow<HouseDBModel>
}