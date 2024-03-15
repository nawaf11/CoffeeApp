package com.example.coffeeapp.domain.mapper

import com.example.coffeeapp.data.local.database.entity.CoffeeEntity
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Reusable
import javax.inject.Inject

@Reusable
class MapCoffeeEntityToCoffeeDomainModel @Inject constructor() {

    operator fun invoke(coffeeDto: CoffeeEntity) : CoffeeDomainModel {

        var ingredients : List<String> = emptyList()
        if (! coffeeDto.ingredientsArray.isNullOrEmpty()) {
            try {
                ingredients = Gson().fromJson(coffeeDto.ingredientsArray,
                    object : TypeToken<List<String>>() {}.type)
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }

        return CoffeeDomainModel(
            id = coffeeDto.id,
            title = coffeeDto.title,
            description = coffeeDto.description,
            imageLink = coffeeDto.imageLink,
            ingredients = ingredients
        )
    }

}