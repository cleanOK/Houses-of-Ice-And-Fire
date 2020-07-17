package com.dmytrod.housesoficefire.data.remote.model

import com.google.gson.annotations.SerializedName

data class HouseResponse(
    @SerializedName("coatOfArms")
    val coatOfArms: String = "",
    @SerializedName("currentLord")
    val currentLord: String = "",
    @SerializedName("diedOut")
    val diedOut: String = "",
    @SerializedName("founded")
    val founded: String = "",
    @SerializedName("founder")
    val founder: String = "",
    @SerializedName("heir")
    val heir: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("overlord")
    val overlord: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("words")
    val words: String = ""
)