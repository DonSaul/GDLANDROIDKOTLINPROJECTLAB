package com.example.recipesapp.data.local

import androidx.room.TypeConverter
import com.example.recipesapp.data.local.entities.IngredientList
import com.google.gson.Gson

class RoomTypeConverters{
    @TypeConverter
    fun convertIngredientListToJSONString(ingredientList: IngredientList): String = Gson().toJson(ingredientList)
    @TypeConverter
    fun convertJSONStringToIngredientList(jsonString: String) = Gson().fromJson(jsonString, IngredientList::class.java)
}