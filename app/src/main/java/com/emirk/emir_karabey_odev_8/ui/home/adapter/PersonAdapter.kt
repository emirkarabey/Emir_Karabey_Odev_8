package com.emirk.emir_karabey_odev_8.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.emir_karabey_odev_8.databinding.ItemPersonBinding
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person

class PersonAdapter(
    private val albumDetailItemClickListener: PersonItemClickListener
) : ListAdapter<Person, PersonViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(
                oldItem: Person,
                newItem: Person
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Person,
                newItem: Person
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding, albumDetailItemClickListener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}