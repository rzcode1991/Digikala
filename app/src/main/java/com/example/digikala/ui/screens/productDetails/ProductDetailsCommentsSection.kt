package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.Comment
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.IconInverter
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkGreen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.orange
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.viewModel.ProductDetailsViewModel

@Composable
fun ProductDetailsCommentsSection(
    productDetails: ProductDetails,
    navHostController: NavHostController,
    viewModel: ProductDetailsViewModel = hiltViewModel()
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.medium
            )
            .background(MaterialTheme.colorScheme.bottomBarColor)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.semiMedium,
                    bottom = MaterialTheme.spacing.semiMedium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.users_comments),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${engToFa(productDetails.commentCount.toString())} ${stringResource(id = R.string.comment)}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.darkCyan,
                fontWeight = FontWeight.Medium
            )

        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ){

            val firstComments = productDetails.comments.take(4)

            items(firstComments){comment ->
                CommentItem(comment = comment, viewModel = viewModel)
            }
            item {
                val productId = productDetails._id
                val commentsCount = productDetails.commentCount.toString()
                ShowMore {
                    navHostController.navigate(Screen.AllComments.withArgs(productId, commentsCount))
                }
            }

        }

    }

}


@Composable
private fun CommentItem(
    comment: Comment,
    viewModel: ProductDetailsViewModel
){

    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

    Card(
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        ),
        shape = MaterialTheme.roundedShape.small,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    text = comment.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.spacing.medium
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.ThumbUp,
                        contentDescription = "",
                        tint = if (comment.star > 2.5){
                            MaterialTheme.colorScheme.darkGreen
                        }else{
                            MaterialTheme.colorScheme.orange
                        },
                        modifier = if (comment.star > 2.5){
                            Modifier
                                .size(18.dp)
                        }else{
                            Modifier
                                .graphicsLayer(scaleY = -1f)
                                .size(18.dp)
                        }
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                    Text(
                        text = if (comment.star > 2.5){
                            stringResource(id = R.string.i_suggest)
                        }else{
                            stringResource(id = R.string.i_dont_suggest)
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = if (comment.star > 2.5){
                            MaterialTheme.colorScheme.darkGreen
                        }else{
                            MaterialTheme.colorScheme.orange
                        },
                        fontWeight = FontWeight.Medium
                    )

                }

                Text(
                    text = comment.description,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.semiDarkText,
                    fontWeight = FontWeight.Medium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                val creationTime = viewModel.getTimeDifferenceFromNow(comment.createdAt)

                Text(
                    text = creationTime,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    text = stringResource(id = R.string.dot_bullet),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.spacing.extraSmall
                        )
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Text(
                    text = comment.userName,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )

            }

        }

    }

}

@Composable
private fun ShowMore(
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .size(
                width = 180.dp,
                height = 200.dp
            )
            .padding(
                end = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.semiSmall,
                top = MaterialTheme.spacing.semiLarge
            )
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        IconInverter(
            painter = rememberAsyncImagePainter(R.drawable.show_more),
            tint = MaterialTheme.colorScheme.darkCyan
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText
        )

    }
}