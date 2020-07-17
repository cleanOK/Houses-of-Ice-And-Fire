package com.dmytrod.housesoficefire.data.repository

import IResponseHandler
import com.dmytrod.housesoficefire.data.db.IceAndFireDatabase
import com.dmytrod.housesoficefire.data.db.model.HouseDBModel
import com.dmytrod.housesoficefire.data.remote.IceAndFireApi
import com.dmytrod.housesoficefire.data.remote.model.HouseResponse
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class HouseRepository(
    private val iceAndFireApi: IceAndFireApi,
    private val iceAndFireDatabase: IceAndFireDatabase,
    private val responseHandler: IResponseHandler
) : IHouseRepository {

    override suspend fun fetchAllFromRemote(): IResponseHandler.Response<List<HouseResponse>> =
        try {
            responseHandler.handleSuccess(iceAndFireApi.getHouses())
        } catch (e: Throwable) {
            Timber.e(e, "failed to fetch All Houses")
            responseHandler.handleException(e)
        }

    override suspend fun loadAllFromDB(): Flow<List<HouseDBModel>> =
        iceAndFireDatabase.houseDao().getAll()

    override suspend fun saveToDB(data: List<HouseResponse>) {
        iceAndFireDatabase.houseDao().insert(data.map { HouseDBModel.from(it) })
    }
}