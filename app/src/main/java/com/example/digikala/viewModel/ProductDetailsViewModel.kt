package com.example.digikala.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.BasketRepository
import com.example.digikala.repository.ProductDetailsRepository
import com.example.digikala.ui.screens.productDetails.ProductDetailsScreenState
import com.example.digikala.utils.DigitHelper.engToFa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.toKotlinDuration

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository,
    private val basketRepository: BasketRepository
): ViewModel() {

    val productDetailsResult = MutableStateFlow<NetworkResult<ProductDetails>>(NetworkResult.Loading())

    val productDetailsScreenState = MutableStateFlow<ProductDetailsScreenState<List<CartItem>>>(ProductDetailsScreenState.Loading)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            basketRepository.wholeCartItems.collectLatest {
                productDetailsScreenState.emit(ProductDetailsScreenState.Success(it))
            }
        }
    }

    fun getProductById(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            productDetailsResult.emit(productDetailsRepository.getProductById(id))
        }
    }

    fun addCartItem(cart: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            basketRepository.addCartItem(cart)
        }
    }

    @Composable
    fun getTimeDifferenceFromNow(creationTime: String): String {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val creationDateTime = ZonedDateTime.parse(creationTime, formatter)
        val currentDateTime = ZonedDateTime.now()

        val duration = java.time.Duration.between(creationDateTime, currentDateTime).toKotlinDuration()

        return when {
            duration.inWholeDays >= 365 -> "${engToFa((duration.inWholeDays / 365).toString())} ${stringResource(id = R.string.years_ago)}"
            duration.inWholeDays >= 30 -> "${engToFa((duration.inWholeDays / 30).toString())} ${stringResource(id = R.string.months_ago)}"
            duration.inWholeDays >= 1 -> "${engToFa((duration.inWholeDays).toString())} ${stringResource(id = R.string.days_ago)}"
            duration.inWholeHours >= 1 -> "${engToFa((duration.inWholeHours).toString())} ${stringResource(id = R.string.hours_ago)}"
            duration.inWholeMinutes >= 1 -> "${engToFa((duration.inWholeMinutes).toString())} ${stringResource(id = R.string.minutes_ago)}"
            else -> stringResource(id = R.string.just_now)
        }
    }

}