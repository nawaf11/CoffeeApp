package com.example.coffeeapp.data.repository

import com.example.coffeeapp.data.local.database.dao.CoffeeDao
import com.example.coffeeapp.data.local.database.entity.CoffeeEntity
import com.example.coffeeapp.data.mapper.MapCoffeeDtoToCoffeeEntityModel
import com.example.coffeeapp.data.remote.NetworkCaller
import com.example.coffeeapp.data.remote.api.CoffeeApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val coffeeApiService: CoffeeApiService,
    private val coffeeDao : CoffeeDao,
    private val mapCoffeeDtoToCoffeeEntityModel : MapCoffeeDtoToCoffeeEntityModel
) {

    suspend fun fetchCoffeeList() {

        val result = NetworkCaller.call {
            coffeeApiService.getCoffeeList()
        }

        if (result.isSuccess() && result.data != null) {
            coffeeDao.deleteAll()

            val mappedList = result.data.map {
                mapCoffeeDtoToCoffeeEntityModel(it)
            }
            coffeeDao.insertAll(mappedList)
        }

    }

    fun observeCoffeeListFlow() : Flow<List<CoffeeEntity>> = coffeeDao.observeCoffeeList()

    suspend fun getCoffeeById(coffeeId : Int) : CoffeeEntity? {
        return coffeeDao.getCoffeeById(coffeeId)
    }

}