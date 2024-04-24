package com.devpro.android54_day11.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devpro.android54_day11.network.api.ApiResponse
import com.devpro.android54_day11.objects.response.comments.CommentResponse
import com.devpro.android54_day11.repositories.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(application: Application,val commentRepository: CommentRepository):AndroidViewModel(application) {

    val commentData = MutableLiveData<ApiResponse<CommentResponse>>()

    fun getComment(){
            commentData.value = ApiResponse.Loading()
            viewModelScope.launch {
                val response = commentRepository.getComments()
                commentData.value = response
            }
    }
}