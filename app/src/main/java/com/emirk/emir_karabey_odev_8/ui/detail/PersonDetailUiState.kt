package com.emirk.emir_karabey_odev_8.ui.detail

import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity

data class PersonDetailUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val person: PersonEntity? = null
)