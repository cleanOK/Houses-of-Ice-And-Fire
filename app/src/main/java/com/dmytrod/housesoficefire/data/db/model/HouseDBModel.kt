package com.dmytrod.housesoficefire.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmytrod.housesoficefire.data.db.model.HouseDBModel.Companion.TABLE_NAME
import com.dmytrod.housesoficefire.data.remote.model.HouseResponse

@Entity(tableName = TABLE_NAME)
data class HouseDBModel(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "words")
    val words: String,
    @ColumnInfo(name = "coatOfArms")
    val coatOfArms: String,
    @ColumnInfo(name = "region")
    val region: String,
    @ColumnInfo(name = "currentLord")
    val currentLord: String,
    @ColumnInfo(name = "diedOut")
    val diedOut: String,
    @ColumnInfo(name = "founded")
    val founded: String,
    @ColumnInfo(name = "founder")
    val founder: String,
    @ColumnInfo(name = "heir")
    val heir: String,
    @ColumnInfo(name = "overlord")
    val overlord: String
) {
    companion object {
        const val TABLE_NAME = "movie"
        fun from(houseResponse: HouseResponse): HouseDBModel = with(houseResponse) {
            HouseDBModel(
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
