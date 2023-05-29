package com.emirk.emir_karabey_odev_8.domain.use_case

import com.bumptech.glide.load.HttpException
import com.emirk.emir_karabey_odev_8.common.Resource
import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetPersonByGroupUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    operator fun invoke(
        personGroup: String
    ): Flow<Resource<List<Person>>> = flow {
        try {
            emit(Resource.Loading())
            val persons = repository.getPersonsByGroup(personGroup)
            emit(Resource.Success(data = persons))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        }
    }
}