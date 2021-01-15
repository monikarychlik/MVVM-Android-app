package com.example.skilltest.ui.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.skilltest.data.UserDao
import com.example.skilltest.ui.main.MainFragment.Companion.USER_ID

class UserDetailsViewModel @ViewModelInject constructor(
    userDao: UserDao,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val user = userDao.getUserById(savedStateHandle[USER_ID]!!).asLiveData()
}