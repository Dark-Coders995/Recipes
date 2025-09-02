package com.agcoding.recipes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agcoding.recipes.screens.viewmodel.MainViewModel
import com.agcoding.recipes.widgets.FavouriteCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel : MainViewModel
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favourites",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ){
            val list = viewModel.favList.collectAsState().value
            LazyColumn {
                items(list.size) { index ->
                    val recipe = list[index]
                    FavouriteCard(recipe)
                }
            }
        }
    }
}