package com.example.recipesapp.utils

import androidx.compose.ui.text.capitalize
import com.example.recipesapp.model.Instruction

fun getIngredients( analyzedInstructions: List<Instruction?>?): String{
    val steps: Int = analyzedInstructions?.get(0)?.steps?.size!!
    var instr = ""

    for (i in 0..<steps) {
        val ingr: Int = analyzedInstructions.get(0)?.steps?.get(i)?.ingredients?.size!!
        for (j in 0..<ingr){
            val word = analyzedInstructions.get(0)?.steps?.get(i)?.ingredients?.get(j)?.name + "\n"
            instr += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
    return instr
}