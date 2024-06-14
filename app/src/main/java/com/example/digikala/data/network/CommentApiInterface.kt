package com.example.digikala.data.network

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.comment.NewComment
import com.example.digikala.data.model.productDetails.Comment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentApiInterface {

    @POST("v1/setNewComment")
    suspend fun setNewComment (
        @Body newComment: NewComment
    ): Response<ResponseResult<String>>

    @GET("v1/getAllProductComments")
    suspend fun getAllProductComments(
        @Query("id") id: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String
    ): Response<ResponseResult<List<Comment>>>

    @GET("v1/getUserComments")
    suspend fun getUserComments(
        @Query("token") token: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String
    ): Response<ResponseResult<List<Comment>>>

}