package com.agcoding.recipes.di

import android.content.Context
import androidx.room.Room
import com.agcoding.recipes.data.RecipeDatabase
import com.agcoding.recipes.data.RecipesDao
import com.agcoding.recipes.network.RecipesApi
import com.agcoding.recipes.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
class AppModule {

    @Singleton
    @Provides
    fun provideRecipesDao(
        recipesDatabase: RecipeDatabase
    ) : RecipesDao = recipesDatabase.recipeDao()

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ) : RecipeDatabase = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        "recipes_database"
    )
        .fallbackToDestructiveMigration(false)
        .build()


    @Singleton
    @Provides
    fun provideRecipesApi() : RecipesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesApi::class.java)
    }
}