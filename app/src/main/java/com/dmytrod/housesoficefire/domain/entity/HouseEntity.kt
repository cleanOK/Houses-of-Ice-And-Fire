package com.dmytrod.housesoficefire.domain.entity

import com.dmytrod.housesoficefire.data.db.model.HouseDBModel

data class HouseEntity(val url: String, val name: String) {
    companion object {
        fun from(dbModel: HouseDBModel) =
            HouseEntity(dbModel.url, dbModel.name)
    }
}