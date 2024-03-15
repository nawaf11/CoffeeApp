package com.example.coffeeapp.ui.root_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.coffeeapp.ui.coffee_graph.CoffeeNavGraph
import com.example.coffeeapp.ui.coffee_graph.CoffeeNavGraphConst

private const val RootGraph_route = "rootGraph_route"

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = CoffeeNavGraphConst.CoffeeGraph_route,
        route = RootGraph_route
    ) {

        CoffeeNavGraph(navController = navController)

    }

}