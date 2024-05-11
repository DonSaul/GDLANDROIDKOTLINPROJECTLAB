package com.example.recipesapp.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String,
    val servings: Int,
    val readyInMinutes: Int,
    val license: String,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val aggregateLikes: Int,
    val healthScore: Double,
    val spoonacularScore: Double,
    val pricePerServing: Double,
    val analyzedInstructions: List<Instruction>,
    val cheap: Boolean,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val gaps: String,
    val glutenFree: Boolean,
    val instructions: String,
    val ketogenic: Boolean,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val sustainable: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val whole30: Boolean,
    val weightWatcherSmartPoints: Int,
    val dishTypes: List<String>,
    val extendedIngredients: List<Ingredient>,
    val summary: String,
    val winePairing: WinePairing,
    val nutrition: Nutrition
)

data class Nutrition(
    val nutrients: List<NutrientX>,
    val ingredients: List<Ingredients>,
    val properties: List<Properties>,
    val flavonoids: List<Properties>
)
data class NutrientX(
    val amount: Double,
    val name: String,
    val percentOfDailyNeeds: Double,
    val unit: String
)

data class Nutrients(
    val name: String,
    val amount: Double,
    val unit: String,
    val percentOfDailyNeeds: Double,
)

data class Ingredients(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String,
    //val nutrients: List<Nutrients>
)

data class Properties(
    val name: String,
    val amount: Double,
    val unit: String,
)

data class Ingredient(
    val id: Int,
    val aisle: String,
    val image: String,
    val consistency: String,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalString: String,
    val originalName: String,
    val amount: Double,
    val unit: String,
    val meta: List<String>,
    val metaInformation: List<String>,
    val measures: Measures
)

data class Measures(
    val us: Measure,
    val metric: Measure
)

data class Measure(
    val amount: Double,
    val unitShort: String,
    val unitLong: String
)

data class Instruction(
    val name: String,
    val steps: List<Step>
)

data class Step(
    val number: Int,
    val step: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>
)

data class Equipment(
    val id: Int,
    val name: String,
    val localizedName: String,
    val image: String
)

data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatch>
)

data class ProductMatch(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val averageRating: Double,
    val ratingCount: Int,
    val score: Double,
    val link: String
)

data class RecipesArray(
    val recipes: List<Recipe>
)