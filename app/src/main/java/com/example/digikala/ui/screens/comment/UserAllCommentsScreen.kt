package com.example.digikala.ui.screens.comment

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.CommentViewModel

@Composable
fun UserAllCommentsScreen(
    navController: NavHostController,
    viewModel: CommentViewModel = hiltViewModel()
){

    LaunchedEffect(true){
        viewModel.getUserCommentsList(USER_TOKEN)
    }

    val allComments = viewModel.userCommentsList.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopSectionWithBackArrowAndText(
                navController = navController,
                title = stringResource(id = R.string.comments)
            )
        }
    ){ paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                )
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.bottomBarColor)
        ){

            item {
                CommentsCountSection(allComments.itemCount.toString())
            }

            items(allComments.itemCount){itemIndex ->
                allComments[itemIndex]?.let {
                    CommentItemView(
                        comment = it,
                        onClick = { productId ->
                            navController.navigate(Screen.ProductDetail.withArgs(productId))
                        }
                    )
                }
            }

            allComments.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            val config = LocalConfiguration.current
                            MyLoading(config.screenHeightDp.dp, true)
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            MyLoading(height = 50.dp, isDark = !isSystemInDarkTheme())
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        Log.e("my_tag", "loading all comments finished or failed.")
                    }
                }
            }

        }

    }

}