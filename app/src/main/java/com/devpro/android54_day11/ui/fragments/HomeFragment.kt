package com.devpro.android54_day11.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpro.android54_day11.R
import com.devpro.android54_day11.adapter.CommentAdapter
import com.devpro.android54_day11.base.BaseFragment
import com.devpro.android54_day11.databinding.FragmentHomeBinding
import com.devpro.android54_day11.network.api.ApiResponse
import com.devpro.android54_day11.repositories.CommentRepository
import com.devpro.android54_day11.utils.PermissionManager
import com.devpro.android54_day11.viewmodels.CommentViewModel
import com.devpro.android54_day11.viewmodels.CommentViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var commentViewModel: CommentViewModel
    private lateinit var mCommentAdapter: CommentAdapter
    private lateinit var locationClient: FusedLocationProviderClient

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
        activity?.let { locationClient = LocationServices.getFusedLocationProviderClient(it) }

        initView()

        commentViewModel.getComment()
        commentViewModel.commentData.observe(viewLifecycleOwner) { response ->

            when (response) {
                is ApiResponse.Loading -> {
                    showLoadingDialog()
                }

                is ApiResponse.Success -> {
                    hideLoadingDialog()
                    mCommentAdapter = CommentAdapter()
                    response.data?.let {
                        mCommentAdapter.updateData(it.comments)
                        binding.rvComment.apply {
                            adapter = mCommentAdapter
                            layoutManager = LinearLayoutManager(this@HomeFragment.context)
                        }
                    }
                }

                is ApiResponse.Failed -> {
                    hideLoadingDialog()
                    Toast.makeText(
                        this@HomeFragment.context,
                        "${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    private fun initView() {
        binding.btnLocation.setOnClickListener {
            activity?.let { it1 ->
                PermissionManager.requestLocationPermission(
                    it1,
                    locationResultLauncher
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private val locationResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return@registerForActivityResult
        } else locationClient.lastLocation.addOnSuccessListener {
            Log.d("TAG", ": lat: ${it.latitude} | long: ${it.longitude}")
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