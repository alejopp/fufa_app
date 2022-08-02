package com.example.fufaapp.presentation.ui.free_tournament.league

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.fufaapp.databinding.FragmentConfigBinding
import com.example.fufaapp.domain.model.Player
import com.example.fufaapp.domain.model.Team
import com.example.fufaapp.presentation.ui.free_tournament.league.adapters.PlayerAdapter
import com.example.fufaapp.util.InputDialogAddPlayer

class ConfigFragment : Fragment() {

    private val configViewModel: ConfigViewModel by activityViewModels()
    private var _binding : FragmentConfigBinding? = null
    private val binding get() = _binding!!
    private var myList = mutableListOf<Player>()

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
        configViewModel.playerList.observe(viewLifecycleOwner){ playerList ->
            binding.rvLeaguePlayers.adapter = PlayerAdapter(playerList)
        }
    }

    private fun listenEvents() {
        binding.fabFreetournamentLeagueAddPlayer.setOnClickListener {
            InputDialogAddPlayer().show(parentFragmentManager,"addPlayerDialog")
        }
        binding.btFreetournamentLeagueStartTournament.setOnClickListener {
        }
    }

}