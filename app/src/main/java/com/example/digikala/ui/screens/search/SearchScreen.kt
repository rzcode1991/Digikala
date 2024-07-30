package com.example.digikala.ui.screens.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.cursorColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        searchViewModel.getAllProducts()
    }

    val isSearching by searchViewModel.isSearching.collectAsState()
    val allProducts by searchViewModel.allProducts.collectAsState()
    val matchedSearchProducts by searchViewModel.matchedSearchProducts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {

        TopSection(
            navController = navController,
            searchViewModel = searchViewModel
        )

        if (allProducts.isEmpty()){
            MyLoading(height = 300.dp, isDark = !isSystemInDarkTheme())
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                if (isSearching){
                    item{
                        MyLoading(height = 200.dp, isDark = !isSystemInDarkTheme())
                    }
                }else{
                    items(matchedSearchProducts) { product ->
                        SearchItemView(
                            product = product,
                            navController = navController
                        )
                    }
                }

            }
        }

    }

}

@Composable
fun TopSection(
    navController: NavHostController,
    searchViewModel: SearchViewModel
) {

    val searchInput by searchViewModel.searchInput.collectAsState()

    TextField(
        value = searchInput,
        onValueChange = {
            searchViewModel.searchInput.value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    searchViewModel.searchInput.value = ""
                }) {
                Icon(
                    Icons.Default.Cancel,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.headlineMedium,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_in_digikala)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
            unfocusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
            focusedIndicatorColor = MaterialTheme.colorScheme.darkCyan,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.searchBarBg,
            cursorColor = MaterialTheme.colorScheme.cursorColor
        ),
        shape = MaterialTheme.roundedShape.small
    )

}