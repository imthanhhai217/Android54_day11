package com.devpro.android54_day11.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.devpro.android54_day11.R
import com.devpro.android54_day11.base.BaseFragment
import com.devpro.android54_day11.databinding.FragmentHomeBinding
import com.devpro.android54_day11.network.api.ApiResponse
import com.devpro.android54_day11.repositories.CommentRepository
import com.devpro.android54_day11.viewmodels.CommentViewModel
import com.devpro.android54_day11.viewmodels.CommentViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var commentViewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        commentViewModel.getComment()
        commentViewModel.commentData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is ApiResponse.Loading->{
                    showLoadingDialog()
                }
                is ApiResponse.Success->{
                    hideLoadingDialog()
                }
                is ApiResponse.Failed->{
                    hideLoadingDialog()
                }

            }
        }
    }

    private fun initViewModel() {
        val application = activity?.application
        val commentRepository = CommentRepository()
        val commentViewModelFactory = CommentViewModelFactory(application!!, commentRepository)
        commentViewModel =
            ViewModelProvider(this, factory = commentViewModelFactory)[CommentViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}