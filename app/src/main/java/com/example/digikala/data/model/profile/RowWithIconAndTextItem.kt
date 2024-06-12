package com.example.digikala.data.model.profile

import androidx.compose.runtime.Composable

data class RowWithIconAndTextItem(
    val titleId: Int,
    val image: @Composable () -> Unit,
    val lastItem: Boolean = false,
    val route: String
)
