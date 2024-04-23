package com.example.digikala.ui.screens.checkout

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import com.example.digikala.data.model.checkout.DayAndDate
import kotlinx.coroutines.CoroutineScope

sealed class CheckoutBottomSheetState {

    data class OnTimeSelect(val date: DayAndDate): CheckoutBottomSheetState()

    @OptIn(ExperimentalMaterialApi::class)
    data class OnCloseOpenBottomSheet(
        val scope: CoroutineScope,
        val bottomSheetState: ModalBottomSheetState
    ): CheckoutBottomSheetState()

}