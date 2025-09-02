package com.agcoding.recipes.repository

import com.agcoding.recipes.data.RecipesDao
import com.agcoding.recipes.model.Favourite
import javax.inject.Inject

class RecipeDbRepository @Inject constructor(
    private val recipeDao: RecipesDao
) {
    fun getFavoriteRecipes() = recipeDao.getFavoriteRecipes()

    suspend fun insertFavourite(favourite: Favourite) = recipeDao.insertFavoriteRecipe(favourite)

    suspend fun deleteFavourite(favourite: Favourite) = recipeDao.deleteFavoriteRecipe(favourite)

    suspend fun isFavourite(id: Int): Boolean = recipeDao.isFavourite(id) != null
}