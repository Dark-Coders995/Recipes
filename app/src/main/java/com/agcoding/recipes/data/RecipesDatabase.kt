package com.agcoding.recipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agcoding.recipes.model.Favourite


@Database(entities = [Favourite::class], version = 1 )
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipesDao
}