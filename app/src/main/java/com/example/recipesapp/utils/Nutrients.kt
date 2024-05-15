package com.example.recipesapp.utils

import com.example.recipesapp.model.Instruction
import com.example.recipesapp.model.Equipment
import com.example.recipesapp.model.Nutrition
import com.example.recipesapp.model.NutrientX


fun getNutrientName(analyzedNutrients: Nutrition?): String{
    var nutrients = ""
    if(analyzedNutrients != null) {
        val totalNutrients: Int = analyzedNutrients.nutrients.size
        for (i in 0..<totalNutrients) {
            val word = "" + analyzedNutrients.nutrients.get(i).name + "\n"
            nutrients += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
    return nutrients
}

fun getNutrientAmount(analyzedNutrients: Nutrition?): String{
    var nutrients = ""
    if(analyzedNutrients != null) {
        val totalNutrients: Int = analyzedNutrients.nutrients.size
        for (i in 0..<totalNutrients) {
            val word = "" + analyzedNutrients.nutrients.get(i).amount + "\n"
            nutrients += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
    return nutrients
}

fun getNutrientUnits(analyzedNutrients: Nutrition?): String{
    var nutrients = ""
    if(analyzedNutrients != null) {
        val totalNutrients: Int = analyzedNutrients.nutrients.size
        for (i in 0..<totalNutrients) {
            val word = "" + analyzedNutrients.nutrients.get(i).unit + "\n"
            nutrients += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
    return nutrients
}

fun getNutrientDailyNeed(analyzedNutrients: Nutrition?): String{
    var nutrients = ""
    if(analyzedNutrients != null) {
        val totalNutrients: Int = analyzedNutrients.nutrients.size
        for (i in 0..<totalNutrients) {
            val word = "" + analyzedNutrients.nutrients.get(i).percentOfDailyNeeds + "% \n"
            nutrients += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
    return nutrients
}