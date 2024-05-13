package com.example.recipesapp.utils

import androidx.compose.ui.text.capitalize
import com.example.recipesapp.model.Ingredients
import com.example.recipesapp.model.Instruction

fun getIngredients( ingredients: List<Ingredients>?): String{
    var instr = ""
    val tIngredients: Int = ingredients?.size!!


    for (i in 0..<tIngredients) {
        val word =
            "- " + ingredients[i].name + "\n"
        instr += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
    return instr
}