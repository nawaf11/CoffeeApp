package com.example.coffeeapp.ui.coffee_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import com.example.coffeeapp.ui.coffee_graph.screen.coffee_details.CoffeeDetailsScreen
import com.example.coffeeapp.ui.coffee_graph.screen.coffee_list.CoffeeListScreen

object CoffeeNavGraphConst {
    internal const val CoffeeGraph_route = "coffeeGraph_route"
    internal const val CoffeeList_route = "coffeeList_route"

    internal const val ARGS_CoffeeDetails_CoffeeId = "args_coffeeData"
    internal const val CoffeeDetails_route_withoutArgs = "coffeeDetails_route"
    internal const val CoffeeDetails_route = "${CoffeeDetails_route_withoutArgs}/{$ARGS_CoffeeDetails_CoffeeId}"
}

fun NavController.navigateToCoffeeDetails(coffeeDomainModel: CoffeeDomainModel) {
    // coffeeDetails_route/3 (where 3 coffeeId)
    navigate(CoffeeNavGraphConst.CoffeeDetails_route_withoutArgs + "/${coffeeDomainModel.id}")
}

fun NavGraphBuilder.CoffeeNavGraph(navController: NavHostController) {

    navigation(
        startDestination = CoffeeNavGraphConst.CoffeeList_route,
        route = CoffeeNavGraphConst.CoffeeGraph_route
    ) {

        composable(CoffeeNavGraphConst.CoffeeList_route) {
            CoffeeListScreen(
                onCoffeeClicked = { coffeeDomainModel ->
                    navController.navigateToCoffeeDetails(coffeeDomainModel)
                }
            )
        }

        composable( CoffeeNavGraphConst.CoffeeDetails_route,
            arguments = listOf(
                navArgument(CoffeeNavGraphConst.ARGS_CoffeeDetails_CoffeeId) {
                    type = NavType.IntType
                    nullable = false
                }
            )
            ) {
            CoffeeDetailsScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

    }

}