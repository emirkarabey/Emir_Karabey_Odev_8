package com.emirk.emir_karabey_odev_8.domain.use_case

import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import javax.inject.Inject

class DeletePersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(
        personId: Int
    ) {
        repository.deletePerson(personId)
    }
}