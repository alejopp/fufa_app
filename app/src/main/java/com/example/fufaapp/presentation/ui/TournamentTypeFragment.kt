package com.example.fufaapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fufaapp.R
import com.example.fufaapp.databinding.FragmentTournamentTypeBinding

class TournamentTypeFragment : Fragment() {

    private var _binding: FragmentTournamentTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTournamentTypeBinding.inflate(inflater,container,false)
        return binding.root
    }



}