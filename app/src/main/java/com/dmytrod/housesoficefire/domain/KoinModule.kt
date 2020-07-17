package com.dmytrod.housesoficefire.domain

import com.dmytrod.housesoficefire.domain.interactor.GetHouseList
import org.koin.dsl.module

val domainModule = module {
    single { GetHouseList(get()) }
}