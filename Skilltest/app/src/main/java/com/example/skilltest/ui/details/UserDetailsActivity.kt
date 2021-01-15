package com.example.skilltest.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.skilltest.R
import com.example.skilltest.databinding.ActivityUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityUserDetailsBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_user_details
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}