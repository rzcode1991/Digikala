package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.MainCategory
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainCategoriesSection(
    viewModel: HomeViewModel = hiltViewModel()
){

    var mainCategoriesList by remember {
        mutableStateOf<List<MainCategory>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val mainCategoriesResult by viewModel.mainCategories.collectAsState()
    when (mainCategoriesResult) {
        is NetworkResult.Success -> {
            mainCategoriesList = mainCategoriesResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e(":::mainCategoriesResult error:::", mainCategoriesResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {

        Text(
            text = stringResource(id = R.string.buy_based_on_category),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.medium
                )
        )

        if (isLoading){
            MyLoading(
                height = 160.dp,
                isDark = true
            )
        }else{
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                for (item in mainCategoriesList){
                    MainCategoryItemView(item)
                }

            }
        }

    }


}

@Composable
fun MainCategoryItemView(item: MainCategory){

    Column(
        modifier = Modifier
            .size(
                width = 100.dp,
                height = 160.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = item.image,
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(
                    vertical = MaterialTheme.spacing.extraSmall
                )
        )

        Text(
            text = item.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.extraSmall
                ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }

}