package com.emirk.emir_karabey_odev_8.domain.use_case

import com.bumptech.glide.load.HttpException
import com.emirk.emir_karabey_odev_8.common.Resource
import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity
import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetPersonDetailUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    operator fun invoke(
        personName: String
    ): Flow<Resource<PersonEntity>> = flow {
        try {
            emit(Resource.Loading())
            val persons = repository.getPersonDetail(personName)
            emit(Resource.Success(data = persons))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        }
    }
}