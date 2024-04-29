package com.example.recipesapp.modules

import com.example.recipesapp.data.RecipesApi
import com.example.recipesapp.data.impl.RecipeRepositoryImpl
import com.example.recipesapp.data.repository.RecipeRepository
import com.example.recipesapp.data.impl.RecipeSearchRepositoryImpl
import com.example.recipesapp.data.repository.RecipeSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRecipeRepository(api: RecipesApi): RecipeRepository{
        return RecipeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesRecipeSearchRepository(api: RecipesApi): RecipeSearchRepository{
        return RecipeSearchRepositoryImpl(api)
    }
}