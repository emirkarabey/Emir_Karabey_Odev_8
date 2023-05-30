package com.emirk.emir_karabey_odev_8.domain.use_case

import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity
import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import javax.inject.Inject

class UpdatePersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(
        personEntity: PersonEntity
    ) {
        repository.updatePerson(personEntity)
    }
}