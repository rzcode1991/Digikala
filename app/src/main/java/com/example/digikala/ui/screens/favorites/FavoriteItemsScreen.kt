package com.example.digikala.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.favorites.FavoriteItem
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa
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

    var isLastItem by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        viewModel.getAllFavorites()
        viewModel.allFavoriteItems.collectLatest { favoriteItemsList ->
            allFavoriteItems = favoriteItemsList
        }
    }

    Scaffold(
        topBar = {
            TopSectionWithBackArrowAndText(
                navController = navController,
                title = stringResource(id = R.string.fav_list)
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.bottomBarColor)
        ){

            item {
                TopSectionFavorites(allFavoriteItems.size)
            }

            itemsIndexed(allFavoriteItems){index, favoriteItem ->
                FavoriteItemView(
                    favoriteItem = favoriteItem,
                    navController = navController,
                    viewModel = viewModel
                )
                isLastItem = allFavoriteItems.size == index + 1
                if (!isLastItem){
                    Spacer(
                        modifier = Modifier
                            .padding(
                                vertical = MaterialTheme.spacing.medium
                            )
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray.copy(alpha = 0.6f))
                    )
                }
            }

        }

    }

}

@Composable
private fun TopSectionFavorites(
    totalFavoriteItems: Int
){

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = stringResource(id = R.string.favorite_items),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "${engToFa(totalFavoriteItems.toString())} ${stringResource(id = R.string.item)}",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

}