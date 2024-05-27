package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.comment.NewComment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentApiInterface {

    @POST("setNewComment")
    suspend fun setNewComment (
        @Body newComment: NewComment
    ): Response<ResponseResult<String>>

}