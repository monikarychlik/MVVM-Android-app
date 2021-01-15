package com.example.skilltest.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.skilltest.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users = repository.getAll().asLiveData()

    fun loadUsers() {
        viewModelScope.launch {
            repository.loadUsers()
        }
    }
}