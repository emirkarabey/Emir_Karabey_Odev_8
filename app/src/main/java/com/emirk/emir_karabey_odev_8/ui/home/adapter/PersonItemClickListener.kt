package com.emirk.emir_karabey_odev_8.ui.home.adapter

import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

interface PersonItemClickListener {
    fun onItemClick(person: Person)
}