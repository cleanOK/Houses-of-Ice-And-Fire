package com.dmytrod.housesoficefire.data

import com.dmytrod.housesoficefire.data.remote.IResponseHandler
import com.dmytrod.housesoficefire.data.remote.ResponseHandler
import android.content.Context
import androidx.room.Room
import com.dmytrod.housesoficefire.BuildConfig
import com.dmytrod.housesoficefire.data.db.IceAndFireDatabase
import com.dmytrod.housesoficefire.data.remote.IceAndFireApi
import com.dmytrod.housesoficefire.data.repository.HouseRepository
import com.dmytrod.housesoficefire.data.repository.IHouseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<IResponseHandler> { ResponseHandler() }
    single { createIceAndFireApi() }
    single { createDatabase(androidContext()) }
    single<IHouseRepository> { HouseRepository(get(), get(), get()) }
}

private fun createIceAndFireApi(): IceAndFireApi = Retrofit.Builder()
    .baseUrl("https://anapioficeandfire.com/api/")
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

private fun createDatabase(appContext: Context) =
    Room.databaseBuilder(appContext, IceAndFireDatabase::class.java, IceAndFireDatabase.DB_FILE_NAME)
        .fallbackToDestructiveMigration()
        .build()