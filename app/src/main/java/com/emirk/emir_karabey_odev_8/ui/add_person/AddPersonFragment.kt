package com.emirk.emir_karabey_odev_8.ui.add_person

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emirk.emir_karabey_odev_8.R
import com.emirk.emir_karabey_odev_8.databinding.FragmentAddPersonBinding
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonFragment : Fragment() {

    private var _binding: FragmentAddPersonBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddPersonViewModel by viewModels()
    private var selectedGroup: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGroupSpinner()
        binding.btnAdd.setOnClickListener {
            val person = Person(
                groupName = selectedGroup.toString(),
                personName = binding.etPersonName.text.toString(),
                personPhoneNo = binding.etPhoneNo.text.toString(),
                personAddress = binding.etAddress.text.toString()
            )
            viewModel.addPerson(person)
            val snackbar = Snackbar.make(requireView(), "Kayıt başarılı!", Snackbar.LENGTH_SHORT)
            snackbar.show()
            findNavController().navigate(AddPersonFragmentDirections.actionAddPersonFragmentToNavHome())
        }
    }

    @SuppressLint("ResourceType")
    private fun setupGroupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerGroup.adapter = adapter

        binding.spinnerGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedGroup = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedGroup = "Aile"
            }
        }
    }
}