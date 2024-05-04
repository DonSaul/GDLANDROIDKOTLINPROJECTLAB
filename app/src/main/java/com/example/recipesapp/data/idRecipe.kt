package com.example.recipesapp.data

object IdRecipe{
    var idRecipe = 0
}

object HistoryEx{
    private var historySr = HistorySearch(search = "Taco")
    var HistoryList =  mutableListOf(historySr,HistorySearch(search = "Fish"))
}

data class HistorySearch(
    val search: String
)