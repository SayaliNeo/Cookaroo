package com.domain.di

import com.domain.usecase.FetchDishCollectionUseCase
import org.koin.dsl.module


val domainModule = module {
    single { FetchDishCollectionUseCase(get()) }
}