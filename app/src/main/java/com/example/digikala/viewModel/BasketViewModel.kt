package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import com.example.digikala.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository
): ViewModel() {



}