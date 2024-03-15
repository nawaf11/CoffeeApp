package com.example.coffeeapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffeeapp.data.local.database.dao.CoffeeDao
import com.example.coffeeapp.data.local.database.entity.CoffeeEntity


@Database(entities = [CoffeeEntity::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {

    abstract fun coffeeDao(): CoffeeDao

    companion object {

        private const val FILE_NAME = "coffee_database"

        fun build(applicationContext : Context) : CoffeeDatabase {
            return Room.databaseBuilder(
                applicationContext,
                CoffeeDatabase::class.java,
                FILE_NAME
            ).build()
        }

    }

}
