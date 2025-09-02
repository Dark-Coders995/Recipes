package com.agcoding.recipes.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agcoding.recipes.model.Favourite
import com.agcoding.recipes.model.random.RecipeItem
import com.agcoding.recipes.repository.RecipeDbRepository
import com.agcoding.recipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : RecipeRepository,
    private val favRepository : RecipeDbRepository
) : ViewModel()
{

    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            favRepository
                .getFavoriteRecipes()
                .distinctUntilChanged()
                .collect { listOfFavs ->
                    if (listOfFavs.isEmpty()){
                        //
                    }
                    else{
                        _favList.value = listOfFavs
                    }
                }
        }
    }

    suspend fun getRecipes() = repository.getRecipes()

    suspend fun searchRecipes(query: String?) = repository.searchRecipes(query)

    fun toggleFavourite(recipe: RecipeItem, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                favRepository.insertFavourite(
                    Favourite(
                        id = recipe.id,
                        title = recipe.title,
                        image = recipe.image,
                        readyInMinutes = recipe.readyInMinutes
                    )
                )
            } else {
                favRepository.deleteFavourite(
                    Favourite(
                        id = recipe.id,
                        title = recipe.title,
                        image = recipe.image,
                        readyInMinutes = recipe.readyInMinutes
                    )
                )
            }
        }
    }

    suspend fun isFavourite(id: Int): Boolean = favRepository.isFavourite(id)

}