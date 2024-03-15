package com.example.coffeeapp.ui.coffee_graph.screen.coffee_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coffeeapp.domain.models.CoffeeDomainModel


@Composable
fun CoffeeList(modifier: Modifier = Modifier, onCoffeeClicked : (CoffeeDomainModel) -> Unit,
    list : List<CoffeeDomainModel>) {

    LazyColumn(modifier) {

        items(list) { item ->
            CoffeeListItem(coffeeDomainModel = item, onClick = {
                onCoffeeClicked(item)
            })
        }
    }

}