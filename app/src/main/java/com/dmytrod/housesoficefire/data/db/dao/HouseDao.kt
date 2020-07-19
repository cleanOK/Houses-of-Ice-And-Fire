package com.dmytrod.housesoficefire.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dmytrod.housesoficefire.data.db.model.HouseDBModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HouseDao : BaseDao<HouseDBModel>() {

    @Query("SELECT * FROM ${HouseDBModel.TABLE_NAME}")
    abstract fun getAll(): Flow<List<HouseDBModel>>

    @Query("SELECT * FROM ${HouseDBModel.TABLE_NAME} WHERE url = :url")
    abstract fun getByUrl(url: String): Flow<HouseDBModel>
}
