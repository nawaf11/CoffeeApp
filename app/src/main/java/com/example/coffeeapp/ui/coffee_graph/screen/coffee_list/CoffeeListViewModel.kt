package com.example.coffeeapp.ui.coffee_graph.screen.coffee_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.domain.usecase.FetchCoffeeListUseCase
import com.example.coffeeapp.domain.usecase.ObserveCoffeeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeListViewModel @Inject constructor(
    private val fetchCoffeeListUseCase : FetchCoffeeListUseCase,
    private val observeCoffeeListUseCase : ObserveCoffeeListUseCase

) : ViewModel() {


    val coffeeListFlow = observeCoffeeListUseCase()

    val isLoadingList = mutableStateOf<Boolean>(false)

    init {
        loadCoffeeList()
    }

    fun loadCoffeeList() {
        viewModelScope.launch {

            isLoadingList.value = true

            fetchCoffeeListUseCase()

            isLoadingList.value = false

        }
    }

}