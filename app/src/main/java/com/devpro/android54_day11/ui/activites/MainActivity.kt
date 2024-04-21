package com.devpro.android54_day11.ui.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.devpro.android54_day11.R
import com.devpro.android54_day11.base.BaseActivity
import com.devpro.android54_day11.databinding.ActivityMainBinding
import com.devpro.android54_day11.ui.fragments.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(HomeFragment.newInstance(),"","")
    }
}