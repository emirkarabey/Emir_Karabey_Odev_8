package com.emirk.emir_karabey_odev_8.ui.home

import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

data class PersonUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val person: List<Person>? = emptyList(),
)