package com.example.coffeeapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coffeeapp.data.local.database.entity.CoffeeEntity
import com.example.coffeeapp.data.local.database.entity.CoffeeTableConsts
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {

    @Query("SELECT * FROM ${CoffeeTableConsts.TABLE_NAME}")
    suspend fun getAll(): List<CoffeeEntity>

    @Query("SELECT * FROM ${CoffeeTableConsts.TABLE_NAME}")
    fun observeCoffeeList() : Flow<List<CoffeeEntity>>

    @Query("SELECT * FROM ${CoffeeTableConsts.TABLE_NAME} WHERE ${CoffeeTableConsts.COLUMN_ID} = :coffeeId")
    suspend fun getCoffeeById(coffeeId: Int): CoffeeEntity?

    @Insert
    suspend fun insertAll(coffeeList: List<CoffeeEntity>)

    @Query("DELETE FROM ${CoffeeTableConsts.TABLE_NAME}")
    suspend fun deleteAll()


}