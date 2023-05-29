package com.emirk.emir_karabey_odev_8.di

import com.emirk.emir_karabey_odev_8.data.repository.PersonRepositoryImpl
import com.emirk.emir_karabey_odev_8.domain.repository.PersonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun personRepository(PersonRepositoryImpl: PersonRepositoryImpl): PersonRepository
}