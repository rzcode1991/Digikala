package com.example.digikala.data.model.profile.settings

import androidx.compose.runtime.Composable

data class SettingsItem(
    val image: @Composable () -> Unit,
    val titleId: Int,
    val url: String,
    val extraComposable: @Composable (() -> Unit)? = null
)
