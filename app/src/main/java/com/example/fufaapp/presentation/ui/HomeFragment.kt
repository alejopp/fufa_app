package com.example.fufaapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fufaapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        listenEvents()
        return binding.root
    }

    private fun listenEvents() {
        binding.btHomeFreeTournament.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTournamentTypeFragment()
            it.findNavController().navigate(action)
        }
    }
}