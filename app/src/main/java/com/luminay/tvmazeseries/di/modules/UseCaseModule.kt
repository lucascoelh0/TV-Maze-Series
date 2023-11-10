package com.luminay.tvmazeseries.di.modules

import com.example.domain.usecases.FavoriteUseCaseImpl
import com.example.domain.usecases.IFavoriteUseCase
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

    @Binds
    fun providesFavoriteUseCase(favoriteUseCase: FavoriteUseCaseImpl): IFavoriteUseCase
}
