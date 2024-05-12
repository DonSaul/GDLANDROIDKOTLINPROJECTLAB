package com.example.recipesapp.utils

import com.example.recipesapp.model.Equipment
import com.example.recipesapp.model.Instruction

fun getEquipment(analyzedEquipment: List<Instruction>?): String{
    var cookware = ""
    if(analyzedEquipment?.isEmpty() == false) {
        val steps: Int = analyzedEquipment.get(0).steps.size


        for (i in 0..<steps) {
            val equip: Int = analyzedEquipment.get(0).steps.get(i).equipment.size
            for (j in 0..<equip) {
                val word =
                    "* " + analyzedEquipment.get(0).steps.get(i).equipment.get(j).name + "\n"
                cookware += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            }
        }
    }
    return cookware
}