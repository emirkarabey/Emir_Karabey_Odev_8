package com.emirk.emir_karabey_odev_8.ui.add_person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emirk.emir_karabey_odev_8.databinding.FragmentAddPersonBinding
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonFragment : Fragment() {

    private var _binding: FragmentAddPersonBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val person = Person(
                groupName = binding.etGroupName.text.toString(),
                personName = binding.etPersonName.text.toString(),
                personPhoneNo = binding.etPhoneNo.text.toString(),
                personAddress = binding.etAddress.text.toString()
            )
            viewModel.addPerson(person)
            findNavController().navigate(AddPersonFragmentDirections.actionAddPersonFragmentToNavHome())
        }
    }
}