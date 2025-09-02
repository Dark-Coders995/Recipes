package com.agcoding.recipes.repository

import android.util.Log
import com.agcoding.recipes.data.DataOrException
import com.agcoding.recipes.model.random.Recipe
import com.agcoding.recipes.model.search.Search
import com.agcoding.recipes.network.RecipesApi
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api : RecipesApi
){
    suspend fun getRecipes() : DataOrException<Recipe , Boolean , Exception>{
        val response = try {
            api.getRandomRecipe()
        }catch (e : Exception){
            Log.e("Error in Fetching Recipes", e.toString(), )
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun searchRecipes(query : String?) : DataOrException<Search , Boolean , Exception>{
        val response = try {
            api.searchRecipes(query = query)
        }catch (e : Exception){

            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}