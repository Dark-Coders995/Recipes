package com.agcoding.recipes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agcoding.recipes.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Query("SELECT * FROM favourite_table")
    fun getFavoriteRecipes(): Flow<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favourite: Favourite)

    @Delete
    suspend fun deleteFavoriteRecipe(favourite: Favourite)

    @Query("SELECT * FROM favourite_table WHERE id = :id LIMIT 1")
    suspend fun isFavourite(id: Int): Favourite?

}