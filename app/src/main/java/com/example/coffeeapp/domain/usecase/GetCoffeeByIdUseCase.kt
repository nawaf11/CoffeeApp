package com.example.coffeeapp.domain.usecase

import com.example.coffeeapp.data.repository.CoffeeRepository
import com.example.coffeeapp.domain.mapper.MapCoffeeEntityToCoffeeDomainModel
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCoffeeByIdUseCase @Inject constructor(
    private val coffeeRepository: CoffeeRepository,
    private val mapCoffeeEntityToCoffeeDomainModel: MapCoffeeEntityToCoffeeDomainModel
) {

    suspend operator fun invoke(coffeeId : Int) : CoffeeDomainModel? {
        val coffeeEntity = coffeeRepository.getCoffeeById(coffeeId)

        if (coffeeEntity != null)
            return mapCoffeeEntityToCoffeeDomainModel(coffeeEntity)

        return null
    }

}