package com.example.digikala.ui.screens.comment

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.digikala.R
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper
import com.example.digikala.viewModel.CommentViewModel

@Composable
fun AllCommentsScreen(
    navController: NavHostController,
    productId: String,
    commentsCount: String,
    viewModel: CommentViewModel = hiltViewModel()
){

    LaunchedEffect(true){
        viewModel.getAllCommentsList(productId)
    }
    val allComments = viewModel.allCommentsList.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AllCommentsTopSection(navController)
        }
    ) { paddingValues ->

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
                CommentsCountSection(commentsCount)
            }

            items(allComments.itemCount){itemIndex ->
                allComments[itemIndex]?.let { CommentItemView(comment = it) }
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

@Composable
private fun CommentsCountSection(
    commentsCount: String
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "${DigitHelper.engToFa(commentsCount)} ${stringResource(
                id = R.string.comment
            )}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText
        )

    }

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