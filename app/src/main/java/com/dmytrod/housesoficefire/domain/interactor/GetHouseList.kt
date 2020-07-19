package com.dmytrod.housesoficefire.domain.interactor

import com.dmytrod.housesoficefire.data.remote.IResponseHandler
import com.dmytrod.housesoficefire.data.repository.IHouseRepository
import com.dmytrod.housesoficefire.domain.Result
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber

class GetHouseList(private val repository: IHouseRepository) :
    FlowInteractor<Unit, List<HouseEntity>> {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun execute(param: Unit): Flow<Result<List<HouseEntity>>> {
        val remoteFlow = flow {
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
                        Result.Failure<List<HouseEntity>>(
                            remoteResponse.remoteError.errorMessageRes,
                            remoteResponse.remoteError.cause
                        )
                    )
                }
            }
        }
        val dbFlow = repository.loadAllFromDB().map {
            Result.Success(it.map { dbModel ->
                HouseEntity.from(dbModel)
            })
        }
        return flowOf(remoteFlow, dbFlow).flattenMerge()
    }


}