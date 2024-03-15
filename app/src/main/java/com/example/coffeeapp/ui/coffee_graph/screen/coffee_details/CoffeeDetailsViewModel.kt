package com.example.coffeeapp.ui.coffee_graph.screen.coffee_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import com.example.coffeeapp.domain.usecase.GetCoffeeByIdUseCase
import com.example.coffeeapp.ui.coffee_graph.CoffeeNavGraphConst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCoffeeByIdUseCase : GetCoffeeByIdUseCase

) : ViewModel() {

    private val coffeeId = savedStateHandle.get<Int>(CoffeeNavGraphConst.ARGS_CoffeeDetails_CoffeeId) ?: 0

    var coffeeDomainModelState = mutableStateOf<CoffeeDomainModel?>(null)

    init {
        viewModelScope.launch {
            coffeeDomainModelState.value = getCoffeeByIdUseCase(coffeeId)
        }
    }

}