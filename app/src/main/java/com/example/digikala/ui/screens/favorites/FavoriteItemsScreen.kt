package com.example.digikala.ui.screens.favorites

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.FavoritesViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteItemsScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel()
){

    var allFavoriteItems by remember {
        mutableStateOf<List<FavoriteItem>>(emptyList())
    }

    LaunchedEffect(true){
        viewModel.getAllFavorites()
        viewModel.allFavoriteItems.collectLatest { favoriteItemsList ->
            allFavoriteItems = favoriteItemsList
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ){

        items(allFavoriteItems){favoriteItem ->
            Text(text = favoriteItem.name)
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}