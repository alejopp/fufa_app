package com.example.fufaapp.presentation.ui.free_tournament.league

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fufaapp.databinding.FragmentLeagueTournamentBinding
import com.example.fufaapp.domain.model.Player
import com.example.fufaapp.presentation.ui.free_tournament.league.adapters.MatchAdapter


class LeagueTournamentFragment : Fragment() {

    companion object{
        const val MATCH_DETAIL_ATTRIBUTES_SIZE = 5
    }

    private val leagueTournamentViewModel: LeagueTournamentViewModel by activityViewModels()
    private var _binding: FragmentLeagueTournamentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueTournamentBinding.inflate(inflater,container,false)
        setInitialConfiguration()
        return binding.root
    }

    private fun setInitialConfiguration(){
        fillTournamentTable()
        leagueTournamentViewModel.createFixture()
        //Set fixture in screen
        binding.rvLeagueTournamentMatches.adapter = MatchAdapter(
            leagueTournamentViewModel.fixtureList,leagueTournamentViewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenEvents()
        observe()
    }

    private fun listenEvents(){
        binding.btUpgrade.setOnClickListener {
            //fillTournamentTable(leagueTournamentViewModel.listPlayers)
            updateTable()
        }
    }

    private fun observe(){
        leagueTournamentViewModel.playerList.observe(viewLifecycleOwner){
            updateTable()
        }
    }

    private fun fillTournamentTable(){
        for(i in 0 until leagueTournamentViewModel.listPlayers.size){
            val tr = TableRow(requireContext())
            for (j in 0..MATCH_DETAIL_ATTRIBUTES_SIZE){
                val tv = TextView(requireContext())
                tv.gravity = Gravity.CENTER
                when(j){
                    0 -> tv.text = leagueTournamentViewModel.listPlayers[i].position.toString()
                    1 -> tv.text = leagueTournamentViewModel.listPlayers[i].team.name.also { tv.gravity = Gravity.START }
                    2 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalsFor.toString()
                    3 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalsAgainst.toString()
                    4 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalDifference.toString()
                    5 -> tv.text = leagueTournamentViewModel.listPlayers[i].points.toString()
                }
                tr.addView(tv)
            }
            binding.tlLeagueTournamentPositionTable.addView(tr)
        }
    }

    private fun updateTable(){
        leagueTournamentViewModel.sortPlayerList()
        for(position in 0 until leagueTournamentViewModel.listPlayers.size){
            val tr = binding.tlLeagueTournamentPositionTable.getChildAt(position+1) as TableRow
            for (tableAttribute in 0..MATCH_DETAIL_ATTRIBUTES_SIZE){
                val tv = tr.getChildAt(tableAttribute) as TextView
                tv.gravity = Gravity.CENTER
                when(tableAttribute){
                    0 -> tv.text = (position + 1).toString()
                    1 -> tv.text = leagueTournamentViewModel.listPlayers[position].team.name.also { tv.gravity = Gravity.START }
                    2 -> tv.text = leagueTournamentViewModel.listPlayers[position].goalsFor.toString()
                    3 -> tv.text = leagueTournamentViewModel.listPlayers[position].goalsAgainst.toString()
                    4 -> tv.text = leagueTournamentViewModel.listPlayers[position].goalDifference.toString()
                    5 -> tv.text = leagueTournamentViewModel.listPlayers[position].points.toString()
                }
            }
        }
    }

}