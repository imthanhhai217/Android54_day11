package com.devpro.android54_day11.repositories

import com.devpro.android54_day11.network.api.ApiResponse
import com.devpro.android54_day11.network.api.GenericApiResponse
import com.devpro.android54_day11.network.api.RetrofitClient
import com.devpro.android54_day11.objects.response.comments.CommentResponse

class CommentRepository : GenericApiResponse() {

    suspend fun getComments():ApiResponse<CommentResponse>{
        return  apiCall { RetrofitClient.getDummyApi.getComments() }
    }
}