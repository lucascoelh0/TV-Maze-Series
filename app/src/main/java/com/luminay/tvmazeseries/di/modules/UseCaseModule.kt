package com.luminay.tvmazeseries.di.modules

import com.example.domain.usecases.IShowsUseCase
import com.example.domain.usecases.ShowsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun providesShowsUseCase(showsUseCase: ShowsUseCaseImpl): IShowsUseCase
}
