package com.example.fufaapp.presentation.ui.free_tournament.league

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fufaapp.R
import com.example.fufaapp.databinding.FragmentTeamSelectionBinding

class TeamSelectionFragment : Fragment() {
    private var _binding: FragmentTeamSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TeamSelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamSelectionBinding.inflate(inflater,container,false)
        return binding.root
    }

}