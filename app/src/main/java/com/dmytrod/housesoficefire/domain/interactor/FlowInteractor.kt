package com.dmytrod.housesoficefire.domain.interactor

import com.dmytrod.housesoficefire.domain.Result
import kotlinx.coroutines.flow.Flow

interface FlowInteractor<T, R> {
    fun execute(param: T): Flow<Result<R>>
}