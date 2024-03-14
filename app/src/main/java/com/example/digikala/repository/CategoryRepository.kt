package com.example.digikala.repository

import com.example.digikala.data.model.category.SubCategory
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.CategoryApiInterface
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: CategoryApiInterface
): BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }

}