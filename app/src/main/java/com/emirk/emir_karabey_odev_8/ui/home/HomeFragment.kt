package com.emirk.emir_karabey_odev_8.ui.home

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.GridLayoutManager
import com.emirk.emir_karabey_odev_8.databinding.FragmentHomeBinding
import com.emirk.emir_karabey_odev_8.domain.ui_model.Person
import com.emirk.emir_karabey_odev_8.ui.home.adapter.PersonAdapter
import com.emirk.emir_karabey_odev_8.ui.home.adapter.PersonItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PersonAdapter
    var parameter: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        parameter = arguments?.getString("parameter")
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectEvent()
        if (parameter.isNullOrEmpty()){
            viewModel.getPersons()
            binding.pageName.text = "Hepsi"
        }
        else if(parameter.equals("Hepsi")){
            viewModel.getPersons()
            binding.pageName.text = parameter
        }else{
            viewModel.getPersonsByGroup(parameter!!)
            binding.pageName.text = parameter
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToAddPersonFragment())
        }
    }

    private fun initRecyclerViewAdapters() {
        adapter = PersonAdapter(object : PersonItemClickListener {
            override fun onItemClick(person: Person) {
                TODO("Not yet implemented")
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvPerson.layoutManager = GridLayoutManager(context, 1)
        rvPerson.adapter = adapter
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        adapter.submitList(uiState.person)
                    }

                    uiState.userMessage?.let {
                        binding.tvError.text = it
                        viewModel.userMessageShown()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}