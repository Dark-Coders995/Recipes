package com.agcoding.recipes.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.agcoding.recipes.model.Favourite
import com.agcoding.recipes.model.random.RecipeItem
import com.agcoding.recipes.screens.viewmodel.MainViewModel
import com.agcoding.recipes.utils.extractSizeFromUrl

@Composable
fun RecipeCard(
    recipe: RecipeItem,
){

    val size = extractSizeFromUrl(recipe.image)
    val aspectRatio = size?.let { it.first.toFloat() / it.second.toFloat() } ?: (4f / 3f)

    Card (
        modifier = Modifier
            .padding(4.dp)
            .width(200.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Transparent
                    ),
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f
                )
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio),
                contentScale = ContentScale.Crop
            )
            Column(
                    modifier = Modifier
                       // .background(Color.Transparent) // single shared background
                        .padding(8.dp)
                        .fillMaxWidth()
            ) {
                Text(
                    text = recipe.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    softWrap = true
                )
                Text(
                    text =  "Ready in ${recipe.readyInMinutes} minutes",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                )
            }
        }
    }
}

@Composable
fun RecipesCard(
    recipe: RecipeItem,
    viewModel : MainViewModel
){
    val size = extractSizeFromUrl(recipe.image)
    val aspectRatio = size?.let { it.first.toFloat() / it.second.toFloat() } ?: (4f / 3f)
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 4.dp
            ){
                AsyncImage(
                    model = recipe.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(aspectRatio)
                )
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(
                    text = recipe.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    softWrap = true
                )
                Text(
                    text =  "Ready in ${recipe.readyInMinutes} minutes",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                )
                var isFavorite by remember { mutableStateOf(false) }
                LaunchedEffect(recipe.id) {
                    isFavorite = viewModel.isFavourite(recipe.id)
                }

                FavoriteButton(
                    isFavorite = isFavorite,
                    onToggle = {
                        isFavorite = !isFavorite
                        if (isFavorite) {
                            viewModel.toggleFavourite(recipe , true)
                        } else {
                            viewModel.toggleFavourite(recipe , false)
                        }
                    }
                )
            }
        }

    }

}



@Composable
fun FavouriteCard(
    recipe: Favourite,
){
    val size = extractSizeFromUrl(recipe.image)
    val aspectRatio = size?.let { it.first.toFloat() / it.second.toFloat() } ?: (4f / 3f)
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 4.dp
            ){
                AsyncImage(
                    model = recipe.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(aspectRatio)
                )
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(
                    text = recipe.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 15.sp,
                    softWrap = true
                )
                Text(
                    text =  "Ready in ${recipe.readyInMinutes} minutes",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                )
            }
        }

    }

}


@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}