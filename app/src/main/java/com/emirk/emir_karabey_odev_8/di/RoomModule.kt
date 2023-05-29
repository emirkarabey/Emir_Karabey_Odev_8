package com.emirk.emir_karabey_odev_8.di

import android.content.Context
import androidx.room.Room
import com.emirk.emir_karabey_odev_8.data.local.PersonDao
import com.emirk.emir_karabey_odev_8.data.local.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val FAV_DATABASE_NAME = "person"

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): PersonDatabase {
        return Room.databaseBuilder(context, PersonDatabase::class.java, FAV_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db: PersonDatabase
    ): PersonDao = db.personDao()
}