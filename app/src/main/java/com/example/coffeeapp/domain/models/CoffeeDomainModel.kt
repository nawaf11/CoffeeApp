package com.example.coffeeapp.domain.models


data class CoffeeDomainModel constructor(
    val id : Int,
    val title : String,
    val imageLink : String?,
    val description : String,
    val ingredients : List<String>
)  {

}