package com.cristian.pruebayape.di

import com.cristian.pruebayape.data.network.RecipesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        internal var BASE_URL = "https://63d6fe5aafbba6b7c9361e6d.mockapi.io/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipes(retrofit: Retrofit): RecipesClient {
        return retrofit.create(RecipesClient::class.java)
    }

}
