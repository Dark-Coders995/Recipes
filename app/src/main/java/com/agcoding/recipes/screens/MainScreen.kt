package com.agcoding.recipes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.agcoding.recipes.data.DataOrException
import com.agcoding.recipes.model.random.Recipe
import com.agcoding.recipes.navigation.BottomNavItem
import com.agcoding.recipes.screens.viewmodel.MainViewModel
import com.agcoding.recipes.widgets.RecipeCard
import com.agcoding.recipes.widgets.RecipesCard


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
    query : String? = null
) {
    val randomRecipes = produceState(
        initialValue = DataOrException(loading = true)
    ){
        value = viewModel.getRecipes()
    }.value
    if (randomRecipes.loading == true){
       Column (
           modifier = Modifier
               .fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           CircularProgressIndicator()
       }
    }
    else if (randomRecipes.data != null){
        MainScreenContent(navController = navController, recipes = randomRecipes.data!! , viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    navController: NavController,
    recipes: Recipe,
    viewModel: MainViewModel
) {

    val searchRecipes  = produceState(
        initialValue = DataOrException(loading = true)
    ){
        value = viewModel.searchRecipes("")
    }.value.data

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recipes",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ){
            Text(
                text = "Popular Recipes",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))

            LazyRow {
                items(recipes.recipes.size) { index ->
                    val recipe = recipes.recipes[index]
                    RecipeCard(recipe = recipe)
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "All Recipes",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(16.dp))

            LazyColumn {
                items(recipes.recipes.size) { index ->
                    val recipe = recipes.recipes[index]
                    RecipesCard(recipe = recipe , viewModel)
                }
            }
        }
    }
}