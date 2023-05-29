package com.emirk.emir_karabey_odev_8.ui.add_person

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import com.emirk.emir_karabey_odev_8.domain.use_case.AddPersonUseCase
import com.emirk.emir_karabey_odev_8.ui.home.PersonUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val addPersonUseCase: AddPersonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PersonUiState())
    val uiState: StateFlow<PersonUiState> = _uiState.asStateFlow()

    fun addPerson(person: Person) = viewModelScope.launch {
        Log.v("AppPersonViewModel", "addperson")
        addPersonUseCase.invoke(person = person)
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }
}