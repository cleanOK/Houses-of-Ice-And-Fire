package com.dmytrod.housesoficefire.domain

import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import com.dmytrod.housesoficefire.domain.interactor.FlowInteractor
import com.dmytrod.housesoficefire.domain.interactor.GetHouseList
import org.koin.dsl.module

val domainModule = module {
    single<FlowInteractor<Unit, List<HouseEntity>>> { GetHouseList(get()) }
}