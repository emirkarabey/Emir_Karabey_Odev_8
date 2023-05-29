package com.emirk.emir_karabey_odev_8.domain.repository

import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

interface PersonRepository {
    suspend fun getPersons(): List<Person>
    suspend fun addPerson(personEntity: PersonEntity)
    suspend fun deletePerson(personId: Int)
    suspend fun getPersonsByGroup(personGroup: String): List<Person>
}