package com.devpro.android54_day11.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devpro.android54_day11.repositories.CommentRepository

class CommentViewModelFactory(
    val application: Application,
    val commentRepository: CommentRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentViewModel(application, commentRepository) as T
    }
}