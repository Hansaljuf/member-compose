package com.capstone.maroonlabkom.ui.Screen.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.maroonlabkom.data.MemberRepository
import com.capstone.maroonlabkom.model.Member
import com.capstone.maroonlabkom.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MemberRepository) : ViewModel() {
    private val _uiState:MutableStateFlow<UiState<List<Member>>> = MutableStateFlow(UiState.Loading)
    val uiState:StateFlow<UiState<List<Member>>>
        get() = _uiState

    fun getAllMember(){
        viewModelScope.launch {
            repository.getAllMember().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect{member->_uiState.value = UiState.Success(member)}
        }
    }
}