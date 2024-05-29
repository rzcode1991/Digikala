package com.example.digikala.ui.screens.comment

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.golden
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.CommentViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewCommentScoreSection(
    viewModel: CommentViewModel
) {

    val context = LocalContext.current

    var scoreRate by remember {
        mutableStateOf("")
    }

    val sliderValue by viewModel.sliderValue.collectAsState()

    Log.e("my_tag", "slider Value is: $sliderValue")

    when (sliderValue) {
        in 0f..0.5f -> {
            scoreRate = ""
            viewModel.onSliderScoreChanged(0)
        }

        in 0.5f..1.5f -> {
            scoreRate = stringResource(R.string.very_bad)
            viewModel.onSliderScoreChanged(1)
        }

        in 1.5f..2.5f -> {
            scoreRate = stringResource(R.string.bad)
            viewModel.onSliderScoreChanged(2)
        }

        in 2.5f..3.5f -> {
            scoreRate = stringResource(R.string.normal)
            viewModel.onSliderScoreChanged(3)
        }

        in 3.5f..4.5f -> {
            scoreRate = stringResource(R.string.good)
            viewModel.onSliderScoreChanged(4)
        }

        in 4.5f..5f -> {
            scoreRate = stringResource(R.string.excellent)
            viewModel.onSliderScoreChanged(5)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.biggerMedium
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.select_score),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.semiDarkText
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = scoreRate,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Slider(
            value = sliderValue,
            onValueChange = {
                viewModel.onSliderValueChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            valueRange = 0f..5f,
            steps = 4,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.golden,
                activeTrackColor = MaterialTheme.colorScheme.golden,
                inactiveTrackColor = Color.LightGray.copy(alpha = 0.6f),
                activeTickColor = Color.LightGray.copy(alpha = 0.6f),
                inactiveTickColor = Color.LightGray.copy(alpha = 0.6f)
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.small
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            for (i in 1..6) {
                Text(
                    text = stringResource(id = R.string.dot_bullet),
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.LightGray.copy(alpha = 0.6f)
                )
            }

        }

        Spacer(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

    }

}