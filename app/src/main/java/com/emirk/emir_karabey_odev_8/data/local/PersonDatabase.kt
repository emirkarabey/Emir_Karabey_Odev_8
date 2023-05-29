package com.emirk.emir_karabey_odev_8.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity

@Database(
    entities = [PersonEntity::class],
    version = 1
)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao
}