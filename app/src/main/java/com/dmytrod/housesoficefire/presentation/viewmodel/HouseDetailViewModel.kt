package com.dmytrod.housesoficefire.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dmytrod.housesoficefire.domain.interactor.GetHouseDetails

class HouseDetailViewModel(
    url: String,
    getHouseDetails: GetHouseDetails
) : ViewModel() {

    val houseDetails = getHouseDetails.execute(url)
        .asLiveData(viewModelScope.coroutineContext)
}