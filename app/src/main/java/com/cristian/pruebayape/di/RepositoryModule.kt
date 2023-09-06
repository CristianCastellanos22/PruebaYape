package com.cristian.pruebayape.di

import com.cristian.pruebayape.data.RecipesRepository
import com.cristian.pruebayape.data.RecipesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository
}