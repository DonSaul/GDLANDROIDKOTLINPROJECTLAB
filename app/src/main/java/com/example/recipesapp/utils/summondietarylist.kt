package com.example.recipesapp.utils



object summonList{

    private val Diet1 = Dietary(
        ID = 1,
        Dietary = "Gluten Free"
    )

    val dietaryList = listOf(Diet1,
        Dietary(ID = 2, Dietary = "Ketogenic"),
        Dietary(ID = 3, Dietary = "Vegetarian"),
        Dietary(ID = 4, Dietary = "Lacto-Vegetarian"),
        Dietary(ID = 5, Dietary = "Ovo-Vegetarian"),
        Dietary(ID = 6, Dietary = "Vegan"),
        Dietary(ID = 7, Dietary = "Pescarian"),
        Dietary(ID = 8, Dietary = "Paleo"),
        Dietary(ID = 9, Dietary = "Primal"),
        Dietary(ID = 10, Dietary = "Low FODMAP"),
        Dietary(ID = 11, Dietary = "Whole 30"),
    )

}
data class Dietary(
    val ID: Int,
    val Dietary: String,

)