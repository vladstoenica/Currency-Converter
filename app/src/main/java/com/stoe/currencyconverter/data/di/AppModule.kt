package com.stoe.currencyconverter.data.di

import android.app.Application
import com.google.gson.Gson
import com.stoe.currencyconverter.data.CurrencyApi
import com.stoe.currencyconverter.main.DefaultMainRepository
import com.stoe.currencyconverter.main.MainRepository
import com.stoe.currencyconverter.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://api.exchangeratesapi.io"

@Module
@InstallIn(SingletonComponent::class)  //asta zice ca dependency-urile traiesc cat aplicatia
object AppModule {

    @Singleton  //avem o sg instanta in toata viata aplicatiei
    @Provides  //we provide this instance so we can inject it
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())   //converts the json response to our data classes
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object: DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfirmed: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}