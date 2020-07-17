package com.dmytrod.housesoficefire.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmytrod.housesoficefire.data.db.dao.HouseDao
import com.dmytrod.housesoficefire.data.db.model.HouseDBModel

@Database(entities = [HouseDBModel::class], version = 1)
abstract class IceAndFireDatabase : RoomDatabase() {
    abstract fun houseDao(): HouseDao
    companion object {
        const val DB_FILE_NAME = "ice_and_fire_db"
    }
}