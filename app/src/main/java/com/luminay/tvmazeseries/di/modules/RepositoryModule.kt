package com.luminay.tvmazeseries.di.modules

import com.example.data.remote.repositories.ShowsRepositoryImpl
import com.example.domain.repositories.IShowsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providesShowsRepository(showsRepository: ShowsRepositoryImpl): IShowsRepository
}
