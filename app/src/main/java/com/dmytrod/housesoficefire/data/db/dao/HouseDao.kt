package com.dmytrod.housesoficefire.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dmytrod.housesoficefire.data.db.model.HouseDBModel

@Dao
abstract class HouseDao : BaseDao<HouseDBModel>() {

    @Query("SELECT * FROM ${HouseDBModel.TABLE_NAME}")
    abstract suspend fun getAll(): List<HouseDBModel>

    @Query("SELECT * FROM ${HouseDBModel.TABLE_NAME} WHERE url = :url")
    abstract suspend fun getByUrl(url: String): HouseDBModel?
}
