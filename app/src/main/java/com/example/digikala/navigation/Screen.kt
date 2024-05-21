package com.example.digikala.navigation

sealed class Screen(val route: String) {

    data object Splash: Screen("splash_screen")
    data object Home: Screen("home_screen")
    data object Category: Screen("category_screen")
    data object Basket: Screen("basket_screen")
    data object Profile: Screen("profile_screen")
    data object WebView: Screen("webView_screen")
    data object Checkout: Screen("checkout_screen")
    data object ConfirmPurchase: Screen("confirm_purchase_screen")
    data object ProductDetail: Screen("product_details_screen")
    data object ProductDescription: Screen("product_description_screen")
    data object NewComment: Screen("new_comment_screen")

    fun withArgs(vararg args: Any): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}