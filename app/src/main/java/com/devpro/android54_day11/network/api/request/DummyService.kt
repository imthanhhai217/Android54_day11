package com.devpro.android54_day11.network.api.request

import com.devpro.android54_day11.constants.ConstantApi
import com.devpro.android54_day11.objects.response.comments.CommentResponse
import retrofit2.Response
import retrofit2.http.GET

interface DummyService {
    @GET(ConstantApi.GET_COMMENT)
    suspend fun getComments(): Response<CommentResponse>
}