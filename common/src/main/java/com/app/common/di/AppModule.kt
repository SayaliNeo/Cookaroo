package com.app.common.di

import com.app.data.repository.DishRepositoryImpl
import com.domain.repository.DishRepository
import org.koin.dsl.module

val appModule = module {
    single<DishRepository> { DishRepositoryImpl() }

    single {  }
}