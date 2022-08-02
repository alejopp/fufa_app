package com.example.fufaapp.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.ViewModel
import com.example.fufaapp.R
import com.example.fufaapp.databinding.DialogInputAddPlayerBinding
import com.example.fufaapp.presentation.ui.free_tournament.league.ConfigViewModel
import com.example.fufaapp.presentation.ui.free_tournament.league.adapters.PlayerAdapter

class InputDialogAddPlayer(): DialogFragment() {

    private val configViewModel: ConfigViewModel by activityViewModels()
    private var _binding: DialogInputAddPlayerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            _binding = DialogInputAddPlayerBinding.bind(inflater.inflate(R.layout.dialog_input_add_player,null))

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(binding.root)
                // Add action buttons
                .setPositiveButton("signin") { _, _ ->
                    val playerName = binding.etvPlayerName.text.toString()
                    val teamName = binding.etvTeamName.text.toString()
                    configViewModel.setPlayer(playerName,teamName)
                }
                .setNegativeButton(R.string.cancel) { _, _ ->

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}