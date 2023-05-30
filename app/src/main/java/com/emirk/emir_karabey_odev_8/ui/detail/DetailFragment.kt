package com.emirk.emir_karabey_odev_8.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.emirk.emir_karabey_odev_8.data.local.entity.PersonEntity
import com.emirk.emir_karabey_odev_8.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    var parameter: String? = null
    var personId: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        parameter = arguments?.getString("personName")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parameter?.let { viewModel.getPersonById(it) }
        collectEvent()

        binding.btnUpdate.setOnClickListener {
            val person = PersonEntity(
                uid = personId!!,
                groupName = binding.etGroupName.text.toString(),
                personName = binding.etPersonName.text.toString(),
                personPhoneNo = binding.etPhoneNo.text.toString(),
                personAddress = binding.etAddress.text.toString()
            )
            viewModel.updatePerson(person)
            val action = DetailFragmentDirections.actionDetailFragmentSelf(binding.etPersonName.text.toString())
            findNavController().navigate(action)
        }
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                    } else {
                        binding.etPersonName.setText(uiState.person!!.personName)
                        binding.etGroupName.setText(uiState.person.groupName)
                        binding.etPhoneNo.setText(uiState.person.personPhoneNo)
                        binding.etAddress.setText(uiState.person.personAddress)
                        personId = uiState.person.uid
                    }
                }
            }
        }
    }
}