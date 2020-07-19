package com.dmytrod.housesoficefire.domain.entity

import com.dmytrod.housesoficefire.data.db.model.HouseDBModel

data class HouseEntity(
    val url: String,
    val name: String,
    val words: String,
    val coatOfArms: String,
    val region: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val overlord: String
) {
    companion object {
        fun from(dbModel: HouseDBModel) = with(dbModel) {
            HouseEntity(
                url,
                name,
                words,
                coatOfArms,
                region,
                currentLord,
                diedOut,
                founded,
                founder,
                heir,
                overlord
            )
        }
    }
}