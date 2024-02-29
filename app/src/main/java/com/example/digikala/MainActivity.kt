package com.example.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.LayoutDirection
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digikala.navigation.BottomNavigationBar
import com.example.digikala.navigation.SetUpNavGraph
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.LocaleUtils

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()

                LocaleUtils.setLocale(LocalContext.current, PERSIAN_LANG)

                CompositionLocalProvider(
                    LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Rtl
                ) {


                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onIconClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    ) {
                        SetUpNavGraph(navController)
                    }


                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DigikalaTheme {
        //
    }
}