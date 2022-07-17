package com.example.fufaapp.presentation.ui.free_tournament.league

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
        listenEvent()
        return binding.root
    }

    private fun listenEvent() {
        binding.fabFreetournamentLeagueAddPlayer.setOnClickListener {
            showAddPlayerInputDialog()
        }
    }

    private fun showAddPlayerInputDialog() {
        val etvAddPlayer = EditText(context)
        etvAddPlayer.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        val addPlayerDialgog = AlertDialog.Builder(context)
            .setTitle("Title")
            .setMessage("Message")
            .setView(etvAddPlayer)
            .setPositiveButton("ok", null)
            .setNegativeButton("cancel",null)
            .create()
        addPlayerDialgog.show()
    }

}