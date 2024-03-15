package com.example.coffeeapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

object CoffeeTableConsts {
    const val TABLE_NAME = "coffee"

    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "title"
    const val COLUMN_IMAGE_LINK = "imageLink"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_INGREDIENTS_ARRAY = "ingredientsArray"
}


@Entity(tableName = CoffeeTableConsts.TABLE_NAME)
data class CoffeeEntity constructor(
    @PrimaryKey @ColumnInfo(name = CoffeeTableConsts.COLUMN_ID) val id : Int,
    @ColumnInfo(name = CoffeeTableConsts.COLUMN_TITLE) val title : String,
    @ColumnInfo(name = CoffeeTableConsts.COLUMN_IMAGE_LINK) val imageLink : String?,
    @ColumnInfo(name = CoffeeTableConsts.COLUMN_DESCRIPTION) val description : String,

    // Json array as string
    @ColumnInfo(name = CoffeeTableConsts.COLUMN_INGREDIENTS_ARRAY) val ingredientsArray : String?
) {
}