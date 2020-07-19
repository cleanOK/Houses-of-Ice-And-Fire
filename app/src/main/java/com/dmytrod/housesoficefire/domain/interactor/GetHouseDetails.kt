package com.dmytrod.housesoficefire.domain.interactor

import com.dmytrod.housesoficefire.data.repository.IHouseRepository
import com.dmytrod.housesoficefire.domain.Result
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHouseDetails(private val houseRepository: IHouseRepository) :
    FlowInteractor<String, HouseEntity> {
    /**
     * @param param url of HouseEntity
     */
    override fun execute(param: String): Flow<Result<HouseEntity>> =
        houseRepository.loadFromDBByUrl(param).map {
            Result.Success<HouseEntity>(HouseEntity.from(it))
        }

}
