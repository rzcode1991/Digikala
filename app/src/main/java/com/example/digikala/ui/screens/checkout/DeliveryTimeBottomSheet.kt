package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.checkout.DayAndDate
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.viewModel.CheckoutViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeliveryTimeBottomSheet(
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    viewModel: CheckoutViewModel = hiltViewModel()
) {

    val availableDays = viewModel.getNextEvenDays()

    val lazyRowState = rememberLazyListState()

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {

            TopSection(
                checkoutViewModel = viewModel,
                scope = scope,
                bottomSheetState = bottomSheetState
            )

            DateSection(
                availableDays = availableDays,
                lazyRowState = lazyRowState,
                scope = scope
            )

            TimeSection(
                availableDays = availableDays,
                lazyRowState = lazyRowState,
                scope = scope,
                bottomSheetState = bottomSheetState,
                checkoutViewModel = viewModel
            )

            BottomSection()

        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TopSection(
    checkoutViewModel: CheckoutViewModel,
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.k1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = stringResource(id = R.string.select_date_to_send),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.darkText,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                    Text(
                        text = stringResource(id = R.string.normal_shipping),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )

                }

            }

            IconButton(onClick = {
                checkoutViewModel.onEvent(
                    CheckoutBottomSheetState.OnCloseOpenBottomSheet(
                        scope = scope,
                        bottomSheetState = bottomSheetState
                    )
                )
            }) {

                Icon(
                    Icons.Default.Close,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.darkText
                )

            }

        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f),
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

    }

}

@Composable
private fun DateSection(
    availableDays: List<DayAndDate>,
    lazyRowState: LazyListState,
    scope: CoroutineScope,
) {

    var selectedDate by remember {
        mutableStateOf(availableDays[0].date)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        availableDays.forEachIndexed { index, days ->

            DayItem(
                day = days.day,
                date = days.date,
                onDateSelected = { date ->
                    selectedDate = date
                    scope.launch {
                        lazyRowState.animateScrollToItem(index)
                    }
                },
                isSelected = selectedDate == days.date
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.biggerMedium))

        }

    }

}

@Composable
private fun DayItem(
    day: String,
    date: Int,
    onDateSelected: (Int) -> Unit,
    isSelected: Boolean
) {

    Column(
        modifier = Modifier
            .clickable {
                onDateSelected(date)
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = day,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) MaterialTheme.colorScheme.darkCyan else Color.Gray
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small2))

        Text(
            text = engToFa(date.toString()),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) MaterialTheme.colorScheme.darkText else Color.Gray
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small2))

        if (isSelected) {
            Icon(
                Icons.Filled.KeyboardArrowUp,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
                tint = MaterialTheme.colorScheme.darkCyan
            )
        }else{
            Spacer(modifier = Modifier.height(25.dp))
        }

    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TimeSection(
    availableDays: List<DayAndDate>,
    lazyRowState: LazyListState,
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    checkoutViewModel: CheckoutViewModel
){

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        userScrollEnabled = false,
        state = lazyRowState
    ){

        itemsIndexed(availableDays){ _, day ->

            SelectedTimeItem(
                dayAndDate = day,
                scope = scope,
                bottomSheetState = bottomSheetState,
                checkoutViewModel = checkoutViewModel,
                isRadioButtonSelected = checkoutViewModel.selectedDay?.date == day.date
            )

        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SelectedTimeItem(
    dayAndDate: DayAndDate,
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    checkoutViewModel: CheckoutViewModel,
    isRadioButtonSelected: Boolean
){

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Card(
        modifier = Modifier
            .width(screenWidth - MaterialTheme.spacing.large)
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.semiLarge,
                start = MaterialTheme.spacing.small2,
                end = MaterialTheme.spacing.small2
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        ),
        shape = MaterialTheme.roundedShape.biggerSmall,
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.spacing.extraSmall
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.semiSmall
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            RadioButton(
                selected = isRadioButtonSelected,
                onClick = {
                    checkoutViewModel.onEvent(
                        CheckoutBottomSheetState.OnTimeSelect(
                            date = dayAndDate
                        )
                    )
                    checkoutViewModel.onEvent(
                        CheckoutBottomSheetState.OnCloseOpenBottomSheet(
                            scope = scope,
                            bottomSheetState = bottomSheetState
                        )
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.darkCyan,
                    unselectedColor = MaterialTheme.colorScheme.darkText
                )
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))

            Text(
                text = "${stringResource(id = R.string.clock_time)} ${engToFa("9")}" +
                        "${stringResource(id = R.string.to)} ${engToFa("22")}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.semiDarkText,
                fontWeight = FontWeight.SemiBold
            )

        }

    }

}

@Composable
private fun BottomSection(){

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.digi_plus_icon),
            contentDescription = "",
            modifier = Modifier
                .size(16.dp)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.semiMedium))

        Text(
            text = stringResource(id = R.string.times_only_for_digiplus),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

}








