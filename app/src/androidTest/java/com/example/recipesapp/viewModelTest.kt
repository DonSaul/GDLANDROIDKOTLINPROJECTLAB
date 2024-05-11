package com.example.recipesapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.viewModel.RecipeByIdViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class viewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModel: RecipeByIdViewModel
    private lateinit var recipeRepository: RecipeRepository

    @Before
    fun setUp(){
        hiltRule.inject()
        viewModel = RecipeByIdViewModel(recipeRepository)
    }


    @Test
    fun testViewModel(){
        val result = viewModel.getRecipeById(640730)
        Assert.assertTrue(result != null)
    }
}