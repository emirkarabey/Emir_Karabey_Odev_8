package com.emirk.emir_karabey_odev_8.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.emir_karabey_odev_8.common.Resource
import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity
import com.emirk.emir_karabey_odev_8.domain.use_case.DeletePersonUseCase
import com.emirk.emir_karabey_odev_8.domain.use_case.GetPersonDetailUseCase
import com.emirk.emir_karabey_odev_8.domain.use_case.UpdatePersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPersonDetailUseCase: GetPersonDetailUseCase,
    private val updatePersonUseCase: UpdatePersonUseCase,
    private val deletePersonUseCase: DeletePersonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PersonDetailUiState())
    val uiState: StateFlow<PersonDetailUiState> = _uiState.asStateFlow()

    fun getPersonById(personName: String) = viewModelScope.launch(Dispatchers.IO) {
        getPersonDetailUseCase.invoke(personName).collect { result ->
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

    fun updatePerson(personEntity: PersonEntity) = viewModelScope.launch {
        updatePersonUseCase.invoke(personEntity)
    }

    fun deletePerson(personId: Int) = viewModelScope.launch {
        deletePersonUseCase.invoke(personId)
    }
}