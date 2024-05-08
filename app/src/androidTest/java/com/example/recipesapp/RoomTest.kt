package com.example.recipesapp

import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipesapp.data.local.RecipesDB
import com.example.recipesapp.data.local.dao.FavoriteDao
import com.example.recipesapp.data.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomTest {
    private lateinit var recipesDB: RecipesDB
    private lateinit var recipesDao: FavoriteDao
    @Before
    fun setUp() {
        recipesDB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipesDB::class.java
        ).build()
        recipesDao = recipesDB.favoriteDao()
    }

    @Test
    fun insertRecipe_displayRecipeInserted() = runBlocking {
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 1))
        val recipe = recipesDao.getFavoriteById(recipeId = 1)?.recipeId

        Assert.assertTrue(recipe != null)
    }

    @Test
    fun insertRecipes_getExpectedSize() = runBlocking {
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 1))
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 2))
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 3))
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 4))

        val recipes = recipesDao.getSizeFavorites()

        Assert.assertTrue(recipes == 4)
    }

    @Test
    fun deleteRecipes_getExpectedSize() = runBlocking {
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 1))
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 2))
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 3))
        recipesDao.insertFavorite(favoriteEntity = FavoriteEntity(recipeId = 4))

        recipesDao.deleteFavoriteById(recipeId = 1)
        recipesDao.deleteFavoriteById(recipeId = 2)
        recipesDao.deleteFavoriteById(recipeId = 3)

        val recipes = recipesDao.getSizeFavorites()

        Assert.assertTrue(recipes == 1)
    }

    @After
    fun tearDown() {
        recipesDB.close()
    }
}