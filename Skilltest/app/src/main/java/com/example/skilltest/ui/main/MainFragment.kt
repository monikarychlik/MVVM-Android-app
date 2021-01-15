package com.example.skilltest.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skilltest.R
import com.example.skilltest.databinding.MainFragmentBinding
import com.example.skilltest.ui.details.UserDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment), UserAdapter.OnItemClickListener {

    companion object {

        const val USER_ID = "com.example.skilltest.USER_ID"

        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<UserViewModel>()
    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        setup()
    }

    private fun setup() {
        val userAdapter = UserAdapter()
        userAdapter.setOnItemClickListener(this@MainFragment)

        binding.recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.users.observe(viewLifecycleOwner) { users ->
            userAdapter.submitList(users)
        }

        viewModel.loadUsers()
    }

    override fun onItemClick(userId: Int) {
        val intent = Intent(this@MainFragment.context, UserDetailsActivity::class.java).apply {
            putExtra(USER_ID, userId)
        }
        startActivity(intent)
    }
}