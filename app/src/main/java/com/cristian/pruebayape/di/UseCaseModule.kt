package com.cristian.pruebayape.di

import com.cristian.pruebayape.domain.RecipesUseCase
import com.cristian.pruebayape.domain.RecipesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideUseCase(
        recipesUseCaseImpl: RecipesUseCaseImpl
    ): RecipesUseCase

}