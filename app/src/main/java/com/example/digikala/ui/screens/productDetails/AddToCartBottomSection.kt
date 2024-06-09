package com.example.digikala.ui.screens.productDetails

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.productDetails.ProductDetails
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.components.DiscountIcon
import com.example.digikala.ui.components.Loading3Dots
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaLightGreen
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import com.example.digikala.utils.DigitHelper.applyDiscount
import com.example.digikala.utils.DigitHelper.engToFaAndSeparateByComma
import com.example.digikala.viewModel.ProductDetailsViewModel

@Composable
fun AddToCartBottomSection(
    productDetails: ProductDetails,
    navHostController: NavHostController,
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
){

    val context = LocalContext.current

    val finalPrice = applyDiscount(
        price = productDetails.price.toLong(),
        discountPercent = productDetails.discountPercent
    )

    var isLoading by remember {
        mutableStateOf(false)
    }
    var isItemInList by remember {
        mutableStateOf(false)
    }
    var isAddCartSuccess by remember {
        mutableStateOf(false)
    }

    val productDetailsScreenState by productDetailsViewModel.productDetailsScreenState.collectAsState()
    var allCartItems by remember {
        mutableStateOf<List<CartItem>>(emptyList())
    }
    when(productDetailsScreenState){
        is ProductDetailsScreenState.Success -> {
            allCartItems = (productDetailsScreenState as ProductDetailsScreenState.Success<List<CartItem>>).data
            for (i in allCartItems){
                if (i.itemId == productDetails._id){
                    isItemInList = true
                }
            }
            isLoading = false
        }
        is ProductDetailsScreenState.Loading -> {
            isLoading = true
        }
        is ProductDetailsScreenState.Error -> {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.bottomBarColor)
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.small,
                    vertical = MaterialTheme.spacing.extraSmall
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Button(
                onClick = {
                    if (Constants.USER_TOKEN.isNotEmpty()){
                        if (isAddCartSuccess || isItemInList){
                            navHostController.navigate(Screen.Basket.route)
                        }else{
                            val cartItem = CartItem(
                                itemId = productDetails._id,
                                discountPercent = productDetails.discountPercent,
                                image = productDetails.imageSlider[0].image,
                                name = productDetails.name,
                                price = productDetails.price,
                                seller = productDetails.seller,
                                count = 1,
                                cartStatus = CartStatus.CURRENT_CART
                            )
                            productDetailsViewModel.addCartItem(cartItem)
                            isAddCartSuccess = true
                            Toast.makeText(
                                context,
                                context.getString(R.string.item_added_to_basket),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else{
                        navHostController.navigate(Screen.Profile.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAddCartSuccess || isItemInList){
                        MaterialTheme.colorScheme.digikalaLightGreen
                    } else {
                        MaterialTheme.colorScheme.digikalaRed
                    },
                    contentColor = MaterialTheme.colorScheme.searchBarBg
                ),
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            ) {

                if (isLoading){
                    MyLoadingButton()
                }else if (!isAddCartSuccess && !isItemInList){
                    Text(
                        text = stringResource(id = R.string.add_to_basket),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                    )
                }else{
                    Text(
                        text = stringResource(id = R.string.go_to_basket),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                    )
                }

            }

            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.End
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    DiscountIcon(discountPercent = productDetails.discountPercent)

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

                    Text(
                        text = engToFaAndSeparateByComma(productDetails.price.toString()),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray.copy(alpha = 0.6f),
                        textDecoration = TextDecoration.LineThrough
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    Text(
                        text = engToFaAndSeparateByComma(finalPrice.toString()),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.darkText
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                    )

                }

            }

        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.6f),
            thickness = 1.dp,
            color = Color.LightGray
        )

    }

}

@Composable
private fun MyLoadingButton(){
    Row(
        modifier = Modifier
            .width(120.dp)
            .padding(MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Loading3Dots(isDark = isSystemInDarkTheme())
    }
}