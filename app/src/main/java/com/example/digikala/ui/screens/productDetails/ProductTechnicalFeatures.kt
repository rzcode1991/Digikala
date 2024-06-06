package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import org.json.JSONObject

@Composable
fun ProductTechnicalFeatures(
    navController: NavHostController,
    jsonString: String?
){
    
    if (jsonString != null){

        Scaffold(
            topBar = {
                TopSectionWithBackArrowAndText(
                    navController = navController,
                    title = stringResource(id = R.string.technical_details)
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ){

                val jsonObject = JSONObject(jsonString)
                val keys = jsonObject.keys()
                while (keys.hasNext()){
                    val key = keys.next()
                    val value = jsonObject.get(key)

                    TechnicalFeatureItem(
                        key = key,
                        value = value.toString()
                    )

                }

            }

        }

    }

}

@Composable
private fun TechnicalFeatureItem(key: String, value: String){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.semiMedium
            )
    ) {

        Text(
            text = key,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(0.3f)
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = Modifier
                .weight(0.6f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.semiMedium
                    )
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray.copy(alpha = 0.6f))
            )

        }

    }

}