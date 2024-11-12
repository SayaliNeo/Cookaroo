package com.domain.repository

import com.domain.model.DishCollection

interface DishRepository {
    suspend fun fetchDishCollection(): DishCollection?
}