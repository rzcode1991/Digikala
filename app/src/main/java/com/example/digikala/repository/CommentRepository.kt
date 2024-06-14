package com.example.digikala.repository

import com.example.digikala.data.model.comment.NewComment
import com.example.digikala.data.model.productDetails.Comment
import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.CommentApiInterface
import com.example.digikala.data.network.NetworkResult
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val api: CommentApiInterface
): BaseApiResponse() {

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> {
        return safeApiCall {
            api.setNewComment(newComment)
        }
    }

    suspend fun getAllProductComments(
        productId: String,
        pageSize: String,
        pageNumber: String
    ): NetworkResult<List<Comment>> {
        return safeApiCall {
            api.getAllProductComments(productId, pageSize, pageNumber)
        }
    }

    suspend fun getUserComments(
        userToken: String,
        pageSize: String,
        pageNumber: String
    ): NetworkResult<List<Comment>> {
        return safeApiCall {
            api.getUserComments(userToken, pageSize, pageNumber)
        }
    }

}