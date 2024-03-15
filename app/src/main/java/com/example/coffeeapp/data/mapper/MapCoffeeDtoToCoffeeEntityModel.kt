package com.example.coffeeapp.data.mapper

import com.example.coffeeapp.data.local.database.entity.CoffeeEntity
import com.example.coffeeapp.data.remote.models.CoffeeDto
import com.google.gson.Gson
import dagger.Reusable
import javax.inject.Inject

@Reusable
class MapCoffeeDtoToCoffeeEntityModel @Inject constructor() {

    operator fun invoke(coffeeDto: CoffeeDto) : CoffeeEntity {

        var ingredientsAsJsonArrayString : String? = null
        if (! coffeeDto.ingredients.isNullOrEmpty()) {
            try {
                ingredientsAsJsonArrayString = Gson().toJson(coffeeDto.ingredients)
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }

        return CoffeeEntity(
            id = coffeeDto.id ?: 0,
            title = coffeeDto.title.orEmpty(),
            description = coffeeDto.description.orEmpty(),
            imageLink = coffeeDto.image,
            ingredientsArray = ingredientsAsJsonArrayString
        )
    }

}