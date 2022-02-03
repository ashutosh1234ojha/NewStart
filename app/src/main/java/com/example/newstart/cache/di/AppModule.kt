package com.example.newstart.cache.di

import android.app.Application
import androidx.room.Room
import com.example.newstart.cache.api.RestaurantApi
import com.example.newstart.cache.data.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Ashutosh Ojha on 31,January,2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(RestaurantApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, RestaurantDatabase::class.java, "restaurant_database")
            .build()

//
//    @Provides
//    @Singleton
//    fun provideRepository(
//        restaurantApi: RestaurantApi,
//        database: RestaurantDatabase
//    ): RestaurantRepository =
//        RestaurantRepository(restaurantApi, database)
}