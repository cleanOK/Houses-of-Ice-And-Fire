package com.dmytrod.housesoficefire.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.dmytrod.housesoficefire.domain.Result
import com.dmytrod.housesoficefire.domain.entity.HouseEntity
import com.dmytrod.housesoficefire.domain.interactor.FlowInteractor
import kotlinx.coroutines.flow.collect

class HouseListViewModel(private val getHouseList: FlowInteractor<Unit, List<HouseEntity>>) :
    ViewModel() {
    val houseList = liveData<HouseListState> {
        emit(HouseListState.Loading)
        getHouseList.execute(Unit).collect {
            when (it) {
                is Result.Success -> emit(HouseListState.Success(it.data))
                is Result.Failure -> emit(HouseListState.Error(it.errorMessageRes))
            }
        }
    }
    val isLoading = houseList.map { it is HouseListState.Loading }



    sealed class HouseListState {
        object Loading : HouseListState()
        data class Success(val list: List<HouseEntity>) : HouseListState()
        data class Error(@StringRes val errorMessageRes: Int, var isHandled: Boolean = false) :
            HouseListState()
        //TODO
//        object Empty: HouseListState()
    }
}