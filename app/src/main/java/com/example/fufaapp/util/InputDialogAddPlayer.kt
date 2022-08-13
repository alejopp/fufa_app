package com.example.fufaapp.util

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.*
import com.example.fufaapp.R
import com.example.fufaapp.databinding.DialogInputAddPlayerBinding
import com.example.fufaapp.presentation.ui.free_tournament.league.LeagueTournamentViewModel

class InputDialogAddPlayer(): DialogFragment() {

    private val leagueTournamentViewModel: LeagueTournamentViewModel by activityViewModels()
    private var _binding: DialogInputAddPlayerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            _binding = DialogInputAddPlayerBinding.bind(inflater.inflate(R.layout.dialog_input_add_player,null))
            builder.setTitle("Agregar jugador y equipo")
            builder.setView(binding.root)
                // Add action buttons
                .setPositiveButton("Ok") { _, _ ->
                    val playerName = binding.etvPlayerName.text.toString()
                    val teamName = binding.etvTeamName.text.toString()
                    leagueTournamentViewModel.setPlayer(playerName,teamName)
                }
                .setNegativeButton(R.string.cancel) { _, _ ->

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}