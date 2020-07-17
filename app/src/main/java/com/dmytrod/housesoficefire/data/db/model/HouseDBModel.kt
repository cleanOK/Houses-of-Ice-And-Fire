package com.dmytrod.housesoficefire.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmytrod.housesoficefire.data.db.model.HouseDBModel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class HouseDBModel(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "name")
    val name: String
) {
    companion object {
        const val TABLE_NAME = "movie"
    }
}
