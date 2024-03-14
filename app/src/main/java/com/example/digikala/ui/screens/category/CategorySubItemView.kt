package com.example.digikala.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.category.CategoryDataItem
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.engToFa

@Composable
fun CategorySubItemView(
    categorySubItem: CategoryDataItem
){

    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(
                horizontal = MaterialTheme.spacing.extraSmall,
                vertical = MaterialTheme.spacing.medium
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.grayCategory
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.semiMedium
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    model = categorySubItem.image,
                    error = painterResource(id = R.drawable.loading_placeholder)
                ),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = categorySubItem.name,
                color = MaterialTheme.colorScheme.darkText,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${engToFa(categorySubItem.count.toString())} ${stringResource(id = R.string.item)}",
                color = Color.Gray,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Normal
            )

        }

    }

}