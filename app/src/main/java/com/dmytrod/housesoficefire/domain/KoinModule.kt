package com.dmytrod.housesoficefire.domain

import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import com.dmytrod.housesoficefire.domain.interactor.FlowInteractor
import com.dmytrod.housesoficefire.domain.interactor.GetHouseDetails
import com.dmytrod.housesoficefire.domain.interactor.GetHouseList
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val HOUSE_LIST = "house_list"
const val HOUSE_DETAILS = "house_details"

val domainModule = module {
    single<FlowInteractor<Unit, List<HouseEntity>>>(named(HOUSE_LIST)) { GetHouseList(get()) }
    single<FlowInteractor<String, HouseEntity>>(named(HOUSE_DETAILS)) { GetHouseDetails(get()) }
}