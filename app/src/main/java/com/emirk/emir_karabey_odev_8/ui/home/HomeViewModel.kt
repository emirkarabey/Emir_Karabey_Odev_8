package com.emirk.emir_karabey_odev_8.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.emir_karabey_odev_8.common.Resource
import com.emirk.emir_karabey_odev_8.domain.use_case.GetPersonByGroupUseCase
import com.emirk.emir_karabey_odev_8.domain.use_case.GetPersonByNameUseCase
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
    private val getPersonByGroupUseCase: GetPersonByGroupUseCase,
    private val getPersonByNameUseCase: GetPersonByNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PersonUiState())
    val uiState: StateFlow<PersonUiState> = _uiState.asStateFlow()

    fun getPersons() = viewModelScope.launch(Dispatchers.IO) {
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

    fun getPersonsByGroup(personGroup: String) = viewModelScope.launch(Dispatchers.IO) {
        getPersonByGroupUseCase.invoke(personGroup).collect { result ->
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

    fun getPersonsByName(personName: String) = viewModelScope.launch(Dispatchers.IO) {
        getPersonByNameUseCase.invoke(personName).collect { result ->
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

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }
}