package com.emirk.emir_karabey_odev_8.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.emir_karabey_odev_8.common.Resource
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import com.emirk.emir_karabey_odev_8.domain.use_case.AddPersonUseCase
import com.emirk.emir_karabey_odev_8.domain.use_case.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    private val addPersonUseCase: AddPersonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PersonUiState())
    val uiState: StateFlow<PersonUiState> = _uiState.asStateFlow()

    fun getFavorites() = viewModelScope.launch(Dispatchers.IO) {
        getPersonUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(userMessage = result.message)
                    }
                }
                is Resource.Loading -> {
                    _uiState.update { state ->
                        state.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(person = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun addPerson(person: Person) = viewModelScope.launch {
        addPersonUseCase.invoke(person = person)
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }
}