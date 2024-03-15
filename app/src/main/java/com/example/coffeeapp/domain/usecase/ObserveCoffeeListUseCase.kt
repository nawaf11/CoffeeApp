package com.example.coffeeapp.domain.usecase

import com.example.coffeeapp.data.repository.CoffeeRepository
import com.example.coffeeapp.domain.mapper.MapCoffeeEntityToCoffeeDomainModel
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class ObserveCoffeeListUseCase @Inject constructor(
    private val coffeeRepository: CoffeeRepository,
    private val mapCoffeeEntityToCoffeeDomainModel : MapCoffeeEntityToCoffeeDomainModel
) {

    operator  fun invoke() : Flow<List<CoffeeDomainModel>> {

        return coffeeRepository.observeCoffeeListFlow().map {
            it.map { coffeeEntity ->
                mapCoffeeEntityToCoffeeDomainModel(coffeeEntity)
            }
        }

    }

}