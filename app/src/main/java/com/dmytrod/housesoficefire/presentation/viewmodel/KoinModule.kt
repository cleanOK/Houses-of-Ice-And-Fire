package com.dmytrod.housesoficefire.presentation.viewmodel

import com.dmytrod.housesoficefire.domain.HOUSE_DETAILS
import com.dmytrod.housesoficefire.domain.HOUSE_LIST
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HouseListViewModel(get(named(HOUSE_LIST))) }
    viewModel { (url: String) -> HouseDetailViewModel(url, get(named(HOUSE_DETAILS))) }
}