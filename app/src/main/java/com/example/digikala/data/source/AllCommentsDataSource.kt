package com.example.digikala.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digikala.data.model.productDetails.Comment
import com.example.digikala.repository.CommentRepository

class AllCommentsDataSource(
    val repository: CommentRepository,
    val productId: String
): PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getAllProductComments(
                productId = productId,
                pageSize = "5",
                pageNumber = nextPageNumber.toString()
            ).data

            LoadResult.Page(
                data = response!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )

        }catch (e: Exception){
            Log.e("my_tag", e.message.toString())
            LoadResult.Error(e)
        }
    }


}