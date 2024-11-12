package com.domain.usecase

import com.domain.common.ResponseState
import com.domain.domain.R
import com.domain.model.DishCollection
import com.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchDishCollectionUseCase(private val repository: DishRepository) {
    suspend operator fun invoke(): Flow<ResponseState<DishCollection>> = flow {
        val dishCollection = repository.fetchDishCollection()
        if (dishCollection != null) {
            emit(ResponseState.SuccessState(dishCollection))
        } else {
            emit(ResponseState.ErrorState(message = R.string.data_fetch_error_msg))
        }
    }
}