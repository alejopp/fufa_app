package com.example.fufaapp.presentation.ui.free_tournament.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fufaapp.R
import com.example.fufaapp.databinding.FragmentConfigBinding
import com.example.fufaapp.domain.model.Player
import com.example.fufaapp.presentation.ui.free_tournament.league.adapters.PlayerAdapter
import com.example.fufaapp.util.InputDialogAddPlayer

class ConfigFragment : Fragment() {

    private val leagueTournamentViewModel: LeagueTournamentViewModel by activityViewModels()
    private var _binding : FragmentConfigBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigBinding.inflate(inflater,container,false)
        listenEvents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        leagueTournamentViewModel.playerList.observe(viewLifecycleOwner){ playerList ->
            binding.rvLeaguePlayers.adapter = PlayerAdapter(playerList)
        }
    }

    private fun listenEvents() {
        binding.fabFreetournamentLeagueAddPlayer.setOnClickListener {
            InputDialogAddPlayer().show(parentFragmentManager,"addPlayerDialog")
        }
        binding.btFreetournamentLeagueStartTournament.setOnClickListener {
            findNavController().navigate(R.id.leagueTournamentFragmentDestination)
        }
    }

}