package com.example.bitirmeprojesi.di

import com.example.bitirmeprojesi.data.datasource.FoodDataSource
import com.example.bitirmeprojesi.data.repo.FoodRepo
import com.example.bitirmeprojesi.retrofit.ApiUtils
import com.example.bitirmeprojesi.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModeule {

    @Provides
    @Singleton
    fun provideFoodRepository(fds:FoodDataSource):FoodRepo{
        return FoodRepo(fds)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(fdao:FoodDao):FoodDataSource{
        return FoodDataSource(fdao)
    }

    @Provides
    @Singleton
    fun provideKisilerDao() : FoodDao {
        return ApiUtils.getFoodDao()
    }
}