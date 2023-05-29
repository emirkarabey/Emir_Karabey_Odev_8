package com.emirk.emir_karabey_odev_8.domain.use_case

import android.util.Log
import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import com.emirk.emir_karabey_odev_8.domain.ui_model.toData
import javax.inject.Inject

class AddPersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend operator fun invoke(
        person: Person
    ) {
        repository.addPerson(person.toData())
        Log.v("AppPersonUseCase", "addperson")
    }
}