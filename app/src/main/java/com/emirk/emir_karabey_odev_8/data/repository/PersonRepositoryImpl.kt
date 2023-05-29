package com.emirk.emir_karabey_odev_8.data.repository

import com.emirk.emir_karabey_odev_8.data.local.PersonDao
import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity
import com.emirk.emir_karabey_odev_8.data.local.entity.toDomain
import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personDao: PersonDao
) : PersonRepository {
    override suspend fun getPersons(): List<Person> {
        return personDao.getAllTracks().map {
            it.toDomain()
        }
    }

    override suspend fun addPerson(personEntity: PersonEntity) {
        personDao.insert(personEntity = personEntity)
    }

    override suspend fun deletePerson(personId: Int) {
        personDao.deletePerson(personId = personId)
    }
}