package com.example.coffeeapp.data.remote.models

import com.google.gson.annotations.SerializedName


/**
 * json sample:
{
"title": "Black Coffee",
"description": "Svart kaffe är så enkelt som det kan bli med malda kaffebönor dränkta i hett vatten, serverat varmt. Och om du vill låta fancy kan du kalla svart kaffe med sitt rätta namn: café noir.",
"ingredients": [
"Coffee"
],
"image": "https://images.unsplash.com/photo-1494314671902-399b18174975?auto=format&fit=crop&q=80&w=1887&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
"id": 1
}
 */
data class CoffeeDto constructor(
    @SerializedName("id") val id : Int?,
    @SerializedName("title") val title : String?,
    @SerializedName("image") val image : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("ingredients") val ingredients : List<String>?,
    )
