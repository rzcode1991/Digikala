package com.example.digikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.R
import com.example.digikala.ui.theme.extraBoldNumber
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.unSelectedBottomBar

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onIconClick: (BottomNavItem) -> Unit
){


    val items = listOf(
        BottomNavItem(
            name = "Home",
            route = Screen.Home.route,
            selectedIcon = painterResource(id = R.drawable.digi_logo),
            deSelectedIcon = painterResource(id = R.drawable.digi_logo)
        ),
        BottomNavItem(
            name = "Category",
            route = Screen.Category.route,
            selectedIcon = painterResource(id = R.drawable.digi_logo),
            deSelectedIcon = painterResource(id = R.drawable.digi_logo)
        ),
        BottomNavItem(
            name = "Basket",
            route = Screen.Basket.route,
            selectedIcon = painterResource(id = R.drawable.digi_logo),
            deSelectedIcon = painterResource(id = R.drawable.digi_logo)
        ),
        BottomNavItem(
            name = "Profile",
            route = Screen.Profile.route,
            selectedIcon = painterResource(id = R.drawable.digi_logo),
            deSelectedIcon = painterResource(id = R.drawable.digi_logo)
        )
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (showBottomBar){
        NavigationBar(
            modifier = Modifier,
            containerColor = Color.White,
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
                        unselectedTextColor = MaterialTheme.colorScheme.unSelectedBottomBar
                    ),
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (selected){
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = item.selectedIcon,
                                    contentDescription = item.name
                                )
                            }else{
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = item.deSelectedIcon,
                                    contentDescription = item.name
                                )
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                )
            }
        }
    }



}