package com.dmytrod.housesoficefire.presentation.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HouseViewModel(get()) }
}