package com.emirk.emir_karabey_odev_8.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.emirk.emir_karabey_odev_8.databinding.ItemPersonBinding
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

class PersonViewHolder(
    private val binding: ItemPersonBinding,
    private val personItemClickListener: PersonItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(person: Person) = binding.apply {
        tvPersonName.text = person.personName
        tvGroupName.text = person.groupName
        tvPhoneNo.text = person.personPhoneNo
        tvAddress.text = person.personAddress
    }
}