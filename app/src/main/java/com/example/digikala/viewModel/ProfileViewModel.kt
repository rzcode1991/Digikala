package com.example.digikala.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.digikala.repository.ProfileRepository
import com.example.digikala.ui.screens.profile.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    var screenState by mutableStateOf(ProfileScreenState.LOGIN_SCREEN)

    var phoneEmailInput by mutableStateOf("")

}