package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.category.SubCategory
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
): ViewModel() {

    val subCategories = MutableStateFlow<NetworkResult<SubCategory>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer(){
        viewModelScope.launch {

            // fire and forget
            launch {
                subCategories.emit(repository.getSubCategories())
            }

        }
    }


}