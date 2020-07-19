package com.dmytrod.housesoficefire.domain.interactor

import com.dmytrod.housesoficefire.data.remote.IResponseHandler
import com.dmytrod.housesoficefire.data.repository.IHouseRepository
import com.dmytrod.housesoficefire.domain.Result
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class GetHouseList(private val repository: IHouseRepository) :
    FlowInteractor<Unit, List<HouseEntity>> {

    @ExperimentalCoroutinesApi
    override fun execute(param: Unit): Flow<Result<List<HouseEntity>>> = flow {
        when (val remoteResponse = repository.fetchAllFromRemote()) {
            is IResponseHandler.Response.Success -> {
                val data = remoteResponse.data
                try {
                    repository.saveToDB(data)
                } catch (e: Throwable) {
                    Timber.e(e, "Failed to store to DB")
                }
            }
            is IResponseHandler.Response.Error -> {
                emit(
                    Result.Failure(
                        remoteResponse.remoteError.errorMessageRes,
                        remoteResponse.remoteError.cause
                    )
                )
            }
        }
        emitAll(repository.loadAllFromDB().map {
            Result.Success(it.map { dbModel ->
                HouseEntity.from(dbModel)
            })
        })
    }

}