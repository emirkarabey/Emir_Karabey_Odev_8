package com.emirk.emir_karabey_odev_8.domain.ui_model

import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity

data class Person(
    val groupName: String,
    val personName: String,
    val personPhoneNo: String,
    val personAddress: String
)

fun Person.toData() = PersonEntity(
    groupName = groupName,
    personName = personName,
    personPhoneNo = personPhoneNo,
    personAddress = personAddress
)