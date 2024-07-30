package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {


    var searchInput = MutableStateFlow("")
    val isSearching = MutableStateFlow(false)

    val allProducts = MutableStateFlow<List<StoreProduct>>(emptyList())

    val matchedSearchProducts: StateFlow<List<StoreProduct>> = searchInput
        .debounce(1000L)
        .onEach { isSearching.update { true } }
        .combine(allProducts){ text, products ->
            if (text.isBlank()){
                emptyList()
            }else{
                products.filter { product ->
                    doesMatchSearchQuery(text, product)
                }
            }
        }
        .onEach { isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            /*allProducts.value*/
            emptyList()
        )
    fun getAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {

            launch {
                homeRepository.getSpecialOffers().data?.let { newProducts ->
                    updateAllProducts(newProducts)
                }
            }

            launch {
                homeRepository.getSpecialSupermarketOffers().data?.let { newProducts ->
                    updateAllProducts(newProducts)
                }
            }

            launch {
                homeRepository.getBestsellerProducts().data?.let { newProducts ->
                    updateAllProducts(newProducts)
                }
            }

            launch {
                homeRepository.getMostVisitedProducts().data?.let { newProducts ->
                    updateAllProducts(newProducts)
                }
            }

            launch {
                homeRepository.getMostFavoriteProducts().data?.let { newProducts ->
                    updateAllProducts(newProducts)
                }
            }

            launch {
                homeRepository.getMostDiscountedProducts().data?.let { newProducts ->
                    updateAllProducts(newProducts)
                }
            }

        }
    }

    private fun doesMatchSearchQuery(query: String, item: StoreProduct): Boolean{
        val matchingCombinations = listOf(
            item.name,
            "${item.name.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
        /*return item.name.startsWith(query)*/
    }

    private fun updateAllProducts(newProducts: List<StoreProduct>){
        allProducts.update { existingProducts ->
            val toBeAddedProducts = mutableListOf<StoreProduct>()
            newProducts.forEach { newProduct ->
                if (newProduct !in existingProducts){
                    toBeAddedProducts += newProduct
                }
            }
            existingProducts + toBeAddedProducts
        }
    }


}