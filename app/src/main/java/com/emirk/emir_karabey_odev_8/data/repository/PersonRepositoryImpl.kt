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
        return personDao.getAllPerson().map {
            it.toDomain()
        }
    }

    override suspend fun addPerson(personEntity: PersonEntity) {
        personDao.insert(personEntity = personEntity)
    }

    override suspend fun deletePerson(personId: Int) {
        personDao.deletePerson(personId = personId)
    }

    override suspend fun getPersonsByGroup(personGroup: String): List<Person> {
        return personDao.getPersonsByGroup(personGroup = personGroup).map {
            it.toDomain()
        }
    }

    override suspend fun getPersonsByName(personName: String): List<Person> {
        return personDao.getPersonsByName(personName = personName).map {
            it.toDomain()
        }
    }

    override suspend fun getPersonDetail(personName: String): PersonEntity {
        return personDao.getPersonDetail(personName = personName)
    }

    override suspend fun updatePerson(personEntity: PersonEntity) {
        return personDao.update(personEntity = personEntity)
    }
}