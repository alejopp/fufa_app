package com.example.fufaapp.presentation.ui.free_tournament.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fufaapp.R
import com.example.fufaapp.databinding.FragmentConfigBinding

class ConfigFragment : Fragment() {

    private val viewModel: ConfigViewModel by viewModels()
    private var _binding : FragmentConfigBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigBinding.inflate(inflater,container,false)
        return binding.root
    }

}