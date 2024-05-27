package com.example.digikala.repository

import com.example.digikala.data.model.comment.NewComment
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

}