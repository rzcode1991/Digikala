package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikala.ui.screens.basket.BasketScreen
import com.example.digikala.ui.screens.category.CategoryScreen
import com.example.digikala.ui.screens.checkout.CheckoutScreen
import com.example.digikala.ui.screens.checkout.ConfirmPurchaseScreen
import com.example.digikala.ui.screens.comment.AllCommentsScreen
import com.example.digikala.ui.screens.comment.NewCommentScreen
import com.example.digikala.ui.screens.favorites.FavoriteItemsScreen
import com.example.digikala.ui.screens.home.HomeScreen
import com.example.digikala.ui.screens.profile.ProfileScreen
import com.example.digikala.ui.screens.splash.SplashScreen
import com.example.digikala.ui.screens.home.WebViewScreen
import com.example.digikala.ui.screens.productDetails.PriceChart
import com.example.digikala.ui.screens.productDetails.ProductDescriptionScreen
import com.example.digikala.ui.screens.productDetails.ProductDetailsScreen
import com.example.digikala.ui.screens.productDetails.ProductTechnicalFeatures
import com.example.digikala.ui.screens.profile.settings.SettingsScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }
        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            val url = navBackStackEntry.arguments?.getString("url")
            url?.let {
                WebViewScreen(navController, it)
            }
        }
        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }
        composable(
            route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("orderPrice") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments!!.getString("orderId")?.let { orderId ->
                navBackStackEntry.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }
        }
        composable(
            route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments!!.getString("productId")?.let { id ->
                ProductDetailsScreen(
                    navController = navController,
                    productId = id
                )
            }
        }
        composable(
            route = Screen.ProductDescription.route + "?productName={productName}?productDescription={productDescription}",
            arguments = listOf(
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("productDescription") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            val productName = navBackStackEntry.arguments?.getString("productName")
            val productDescription = navBackStackEntry.arguments?.getString("productDescription")
            ProductDescriptionScreen(
                productName = productName,
                productDescription = productDescription,
                navController = navController
            )
        }
        composable(
            route = Screen.ProductTechnicalFeatures.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            val jsonString = navBackStackEntry.arguments?.getString("jsonString")
            ProductTechnicalFeatures(
                navController = navController,
                jsonString = jsonString
            )
        }
        composable(
            route = Screen.NewCommentScreen.route + "/{productId}/{productName}/{productImage}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("productImage") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments!!.getString("productId")?.let { productId ->
                navBackStackEntry.arguments!!.getString("productName")?.let { productName ->
                    navBackStackEntry.arguments!!.getString("productImage")?.let { productImage ->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            productImage = productImage
                        )
                    }
                }
            }
        }
        composable(
            route = Screen.AllComments.route + "/{productId}/{commentsCount}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("commentsCount") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments!!.getString("productId")?.let { id ->
                navBackStackEntry.arguments!!.getString("commentsCount")?.let { commentsCount ->
                    AllCommentsScreen(
                        navController = navController,
                        productId = id,
                        commentsCount = commentsCount
                    )
                }
            }
        }
        composable(
            route = Screen.PriceChart.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            val jsonString = navBackStackEntry.arguments?.getString("jsonString")
            PriceChart(
                navController = navController,
                jsonString = jsonString
            )
        }
        composable(
            route = Screen.FavoriteScreen.route
        ) {
            FavoriteItemsScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.SettingScreen.route
        ) {
            SettingsScreen(
                navController = navController
            )
        }
    }
}