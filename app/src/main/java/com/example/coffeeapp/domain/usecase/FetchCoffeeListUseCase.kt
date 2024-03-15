package com.example.coffeeapp.domain.usecase

import com.example.coffeeapp.data.repository.CoffeeRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FetchCoffeeListUseCase @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) {

    suspend operator  fun invoke() {
        coffeeRepository.fetchCoffeeList()
    }

}