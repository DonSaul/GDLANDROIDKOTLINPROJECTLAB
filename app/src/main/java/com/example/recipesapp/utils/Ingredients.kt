package com.example.recipesapp.utils

import androidx.compose.ui.text.capitalize
import com.example.recipesapp.model.Ingredients
import com.example.recipesapp.model.Instruction

fun getIngredients( ingredients: List<Ingredients>?): String{
    var instr = ""
    val tIngredients: Int? = ingredients?.size
    println("testing " + tIngredients)

    /*for (i in 0..<tIngredients) {
        val word =
            "- " + ingredients[i].name + "\n"
        instr += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        //val ingr: Int = analyzedInstructions.get(0)?.steps?.get(i)?.ingredients?.size!!
        /*for (j in 0..<ingr) {
            val word =
                "- " + analyzedInstructions.get(0)?.steps?.get(i)?.ingredients?.get(j)?.name + "\n"
            instr += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }*/
    }*/
    return instr
}