package com.example.fufaapp.presentation.ui.free_tournament.league

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.fufaapp.databinding.FragmentLeagueTournamentBinding
import com.example.fufaapp.presentation.ui.free_tournament.league.adapters.MatchAdapter


class LeagueTournamentFragment : Fragment() {

    companion object{
        const val MATCH_DETAIL_ATRIBUTES_SIZE = 5
    }

    private val leagueTournamentViewModel: LeagueTournamentViewModel by activityViewModels()
    private var _binding: FragmentLeagueTournamentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueTournamentBinding.inflate(inflater,container,false)
            for(i in 0 until leagueTournamentViewModel.listPlayers.size){
                val tr = TableRow(requireContext())
                for (j in 0..MATCH_DETAIL_ATRIBUTES_SIZE){
                    val tv = TextView(requireContext())
                    tv.gravity = Gravity.CENTER
                    when(j){
                        0 -> tv.text = leagueTournamentViewModel.listPlayers[i].position.toString()
                        1 -> tv.text = leagueTournamentViewModel.listPlayers[i].team.name.also { tv.gravity = Gravity.START }
                        2 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalsFor.toString()
                        3 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalsAgainst.toString()
                        4 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalDifference.toString()
                        5 -> tv.text = leagueTournamentViewModel.listPlayers[i].goalDifference.toString()
                    }
                    tr.addView(tv)
                }
                binding.tlLeagueTournamentPositionTable.addView(tr)
            }

        //Create fixture
        leagueTournamentViewModel.createFixture()
        //Set fixture in screen
        binding.rvLeagueTournamentMatches.adapter = MatchAdapter(leagueTournamentViewModel.fixtureList)

        return binding.root
    }



}