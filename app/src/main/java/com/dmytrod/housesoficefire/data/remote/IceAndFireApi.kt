package com.dmytrod.housesoficefire.data.remote

import com.dmytrod.housesoficefire.data.remote.model.HouseResponse
import retrofit2.http.GET

interface IceAndFireApi {

    @GET("houses")
    suspend fun getHouses() : List<HouseResponse>
}