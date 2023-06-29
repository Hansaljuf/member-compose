package com.capstone.maroonlabkom.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.maroonlabkom.data.MemberRepository
import com.capstone.maroonlabkom.ui.Screen.Home.HomeViewModel
import com.capstone.maroonlabkom.ui.Screen.detail.DetailViewModel

class ViewModelFactory(private val repository: MemberRepository):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class" + modelClass.name)
    }
}