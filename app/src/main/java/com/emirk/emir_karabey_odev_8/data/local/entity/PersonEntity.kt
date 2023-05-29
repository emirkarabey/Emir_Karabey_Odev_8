package com.emirk.emir_karabey_odev_8.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "groupName") val groupName: String,
    @ColumnInfo(name = "personName") val personName: String,
    @ColumnInfo(name = "personPhoneNo") val personPhoneNo: String,
    @ColumnInfo(name = "personAddress") val personAddress: String
)

fun PersonEntity.toDomain() = Person(
    groupName = groupName,
    personName = personName,
    personPhoneNo = personPhoneNo,
    personAddress = personAddress
)