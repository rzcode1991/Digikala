package com.example.digikala.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.unSelectedBottomBar
import com.example.digikala.utils.Constants
import com.example.digikala.utils.DigitHelper.engToFa
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onIconClick: (BottomNavItem) -> Unit,
    basketViewModel: BasketViewModel = hiltViewModel()
){

    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    val totalCountForCurrentCartItems by basketViewModel.totalCountForCurrentCartItems.collectAsState(0)

    val items = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(id = R.drawable.home_fill),
            deSelectedIcon = painterResource(id = R.drawable.home_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.category),
            route = Screen.Category.route,
            selectedIcon = painterResource(id = R.drawable.category_fill),
            deSelectedIcon = painterResource(id = R.drawable.category_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.basket),
            route = Screen.Basket.route,
            selectedIcon = painterResource(id = R.drawable.cart_fill),
            deSelectedIcon = painterResource(id = R.drawable.cart_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.my_digikala),
            route = Screen.Profile.route,
            selectedIcon = painterResource(id = R.drawable.user_fill),
            deSelectedIcon = painterResource(id = R.drawable.user_outline)
        )
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (showBottomBar){
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.bottomBarColor,
            tonalElevation = 5.dp
        ) {
            items.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onIconClick(item)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.selectedBottomBar,
                        selectedTextColor = MaterialTheme.colorScheme.selectedBottomBar,
                        unselectedIconColor = MaterialTheme.colorScheme.unSelectedBottomBar,
                        unselectedTextColor = MaterialTheme.colorScheme.unSelectedBottomBar,
                        indicatorColor = Color.White
                    ),
                    icon = {
                        if (item.route == Screen.Basket.route && totalCountForCurrentCartItems > 0){
                            IconWithLargeBadge(
                                item = item,
                                totalCountForCurrentCartItems = totalCountForCurrentCartItems,
                                isSelected = selected
                            )
                        }else{
                            if (selected){
                                Icon(
                                    modifier = Modifier.height(22.dp),
                                    painter = item.selectedIcon,
                                    contentDescription = item.name
                                )
                            }else{
                                Icon(
                                    modifier = Modifier.height(22.dp),
                                    painter = item.deSelectedIcon,
                                    contentDescription = item.name
                                )
                            }
                        }
                    },
                    label = {
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                )
            }
        }
    }


}