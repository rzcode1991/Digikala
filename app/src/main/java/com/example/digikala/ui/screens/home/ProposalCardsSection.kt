package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProposalCardsSection(
    viewModel: HomeViewModel = hiltViewModel()
){

    var proposalCardsList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    val proposalCardsResult by viewModel.proposalCards.collectAsState()
    when (proposalCardsResult) {
        is NetworkResult.Success -> {
            proposalCardsList = proposalCardsResult.data ?: emptyList()
            isLoading = false
        }
        is NetworkResult.Error -> {
            Log.e(":::proposalCardsList error:::", proposalCardsResult.message.toString())
            isLoading = false
        }
        is NetworkResult.Loading -> {
            isLoading = true
        }
    }

    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
            .padding(MaterialTheme.spacing.small)
    ) {

        for (item in proposalCardsList){
            ProposalCardItemView(item)
        }

    }


}

@Composable
fun ProposalCardItemView(proposalCard: Slider){

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(140.dp)
            .padding(MaterialTheme.spacing.small),
        shape = MaterialTheme.roundedShape.semiMedium
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = proposalCard.image,
                error = painterResource(id = R.drawable.loading_placeholder)
            ),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}