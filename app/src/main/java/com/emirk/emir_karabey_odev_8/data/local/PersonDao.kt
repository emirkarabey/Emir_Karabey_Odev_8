package com.emirk.emir_karabey_odev_8.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity

@Dao
interface PersonDao {
    @Insert
    suspend fun insert(personEntity: PersonEntity)

    @Update
    suspend fun update(personEntity: PersonEntity)

    @Query("DELETE FROM person WHERE uid=:personId")
    suspend fun deletePerson(personId: Int)

    @Query("SELECT * FROM person ORDER BY uid DESC")
    fun getAllTracks(): List<PersonEntity>
}