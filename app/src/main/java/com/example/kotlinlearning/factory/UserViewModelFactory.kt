package com.example.kotlinlearning.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlearning.viewModel.UserViewModel

class UserViewModelFactory(private var name: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(name) as T
        }
      throw IllegalAccessException("ViewModel Class not Found")
    }
}