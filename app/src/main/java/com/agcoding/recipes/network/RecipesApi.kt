package com.agcoding.recipes.network

import com.agcoding.recipes.model.random.Recipe
import com.agcoding.recipes.model.search.Search
import com.agcoding.recipes.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RecipesApi{

    @GET("complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String?,
        @Query("apiKey") apiKey: String = API_KEY
    ): Search

    @GET("random")
    suspend fun getRandomRecipe(
        @Query("number") number: Int = 5,
        @Query("apiKey") apiKey: String = API_KEY

    ): Recipe
}