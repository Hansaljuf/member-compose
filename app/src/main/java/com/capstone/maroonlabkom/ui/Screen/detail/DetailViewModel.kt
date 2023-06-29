package com.capstone.maroonlabkom.ui.Screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.maroonlabkom.data.MemberRepository
import com.capstone.maroonlabkom.model.Member
import com.capstone.maroonlabkom.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MemberRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Member>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Member>>
        get() = _uiState

    fun getMemberById(memberId:Int){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMemberById(memberId))
        }
    }
}