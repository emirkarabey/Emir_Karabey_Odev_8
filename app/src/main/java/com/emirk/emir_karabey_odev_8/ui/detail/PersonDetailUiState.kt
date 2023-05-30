package com.emirk.emir_karabey_odev_8.ui.detail

import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

data class PersonDetailUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val person: Person? = null
)