package com.dmytrod.housesoficefire.data

import IResponseHandler
import ResponseHandler
import com.dmytrod.housesoficefire.BuildConfig
import com.dmytrod.housesoficefire.data.remote.IceAndFireApi
import com.dmytrod.housesoficefire.data.repository.HouseRepository
import com.dmytrod.housesoficefire.data.repository.IHouseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<IResponseHandler> { ResponseHandler() }
    single { createIceAndFireApi() }
    single<IHouseRepository> { HouseRepository() }
}

private fun createIceAndFireApi(): IceAndFireApi = Retrofit.Builder()
    .baseUrl("https://anapioficeandfire.com/api")
    .addConverterFactory(GsonConverterFactory.create())
    .client(createClient())
    .build()
    .create(IceAndFireApi::class.java)

private fun createClient(): OkHttpClient = OkHttpClient.Builder()
    .apply {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(httpLoggingInterceptor)
        }
    }
    .build()
