package com.example.digikala.repository

import com.example.digikala.data.network.BaseApiResponse
import com.example.digikala.data.network.CommentApiInterface
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val api: CommentApiInterface
): BaseApiResponse() {



}