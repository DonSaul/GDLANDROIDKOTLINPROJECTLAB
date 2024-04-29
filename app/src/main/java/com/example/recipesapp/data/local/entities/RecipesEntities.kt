package com.example.recipesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "instructions")
    val instructions: String,
    val ingredients: IngredientList
)

data class Ingredient(
    val ingredient:String,
)

data class IngredientList(
    val ingredients: List<Ingredient>
)