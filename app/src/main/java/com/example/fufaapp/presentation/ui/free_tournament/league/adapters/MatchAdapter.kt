package com.example.fufaapp.presentation.ui.free_tournament.league.adapters

import android.opengl.Visibility
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fufaapp.databinding.ItemMatchBinding
import com.example.fufaapp.domain.model.Match
import com.example.fufaapp.presentation.ui.free_tournament.league.LeagueTournamentFragment
import com.example.fufaapp.presentation.ui.free_tournament.league.LeagueTournamentViewModel
import java.lang.Integer.parseInt

class MatchAdapter(
    private val matches: List<List<String>>,
    private val leagueTournamentViewModel: LeagueTournamentViewModel)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: ItemMatchBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(ItemMatchBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val matchesPerRound = leagueTournamentViewModel.listPlayers.size / LeagueTournamentViewModel.TEAMS_PER_MATCH
        if(position % matchesPerRound != 0){
            holder.binding.tvRound.visibility = View.GONE
        } else{
            holder.binding.tvRoundNumber.text = ((position / matchesPerRound) + 1).toString()
        }
        //holder.binding.tvRoundNumber.text = (position + 1).toString()
        holder.binding.tvHt.text = matches[position][0]
        holder.binding.tvVt.text = matches[position][1]

        holder.binding.etvLeagueTournamentHomeGoals.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val homeTeam = matches[holder.adapterPosition][0]
                val visitorTeam = matches[holder.adapterPosition][1]
                //Find home and visitor teams goals
                val homeTeamFavorGoals = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsFor
                val visitorTeamAgainstGoals = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }!!.goalsAgainst

                //Update home and visitor teams goals
                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }?.goalsFor = homeTeamFavorGoals + parseInt(s.toString())

                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }?.goalsAgainst = visitorTeamAgainstGoals + parseInt(s.toString())

                //Find favor/against home goals
                val homeFavorGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsFor

                val homeAgainstGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsAgainst

                //Update home goal difference
                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }?.goalDifference = homeFavorGoalsUpdated - homeAgainstGoalsUpdated

                //Find favor/against visitor goals
                val visitorFavorGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsFor

                val visitorAgainstGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }!!.goalsAgainst

                //Update visitor goal difference
                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }?.goalDifference = visitorFavorGoalsUpdated - visitorAgainstGoalsUpdated

                //Update home/visitor team points
                if(holder.binding.etvLeagueTournamentVisitorGoals.text.isNotBlank()){
                    val homeGoals = holder.binding.etvLeagueTournamentHomeGoals.text as Int
                    val visitorGoals = holder.binding.etvLeagueTournamentVisitorGoals.text as Int
                    val homeTeamPoints = leagueTournamentViewModel.listPlayers.find {
                        it.team.name == homeTeam
                    }!!.points
                    val visitorTeamPoints = leagueTournamentViewModel.listPlayers.find {
                        it.team.name == visitorTeam
                    }!!.points

                    if (homeGoals - visitorGoals > 0){
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == homeTeam
                        }?.points = homeTeamPoints + Match.WIN_POINTS
                    } else if (homeGoals - visitorGoals == 0){
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == homeTeam
                        }?.points = homeTeamPoints + Match.TIE_POINTS
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == visitorTeam
                        }?.points = visitorTeamPoints + Match.TIE_POINTS
                    }
                    else{
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == visitorTeam
                        }?.points = visitorTeamPoints + Match.WIN_POINTS
                    }
                    leagueTournamentViewModel.updatePlayerListTable(leagueTournamentViewModel.listPlayers)
                }
            }
            override fun afterTextChanged(s: Editable?){}

        })

        holder.binding.etvLeagueTournamentVisitorGoals.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val homeTeam = matches[holder.adapterPosition][0]
                val visitorTeam = matches[holder.adapterPosition][1]

                //Find home and visitor teams goals
                val homeTeamAgainstGoals = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsAgainst
                val visitorTeamFavorGoals = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }!!.goalsFor

                //Update home and visitor teams goals
                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }?.goalsAgainst = homeTeamAgainstGoals + parseInt(s.toString())

                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }?.goalsFor = visitorTeamFavorGoals + parseInt(s.toString())

                //Find favor/against home goals
                val homeFavorGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsFor

                val homeAgainstGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }!!.goalsAgainst

                //Update home goal difference
                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == homeTeam
                }?.goalDifference = homeFavorGoalsUpdated - homeAgainstGoalsUpdated

                //Find favor/against visitor goals
                val visitorFavorGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }!!.goalsFor

                val visitorAgainstGoalsUpdated = leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }!!.goalsAgainst

                //Update visitor goals difference
                leagueTournamentViewModel.listPlayers.find {
                    it.team.name == visitorTeam
                }?.goalDifference = visitorFavorGoalsUpdated - visitorAgainstGoalsUpdated

                //Update home/visitor team points
                if(holder.binding.etvLeagueTournamentHomeGoals.text.isNotBlank()){
                    val homeGoals = parseInt(holder.binding.etvLeagueTournamentHomeGoals.text.toString())
                    val visitorGoals = parseInt(holder.binding.etvLeagueTournamentVisitorGoals.text.toString())
                    val homeTeamPoints = leagueTournamentViewModel.listPlayers.find {
                        it.team.name == homeTeam
                    }!!.points
                    val visitorTeamPoints = leagueTournamentViewModel.listPlayers.find {
                        it.team.name == visitorTeam
                    }!!.points

                    if (homeGoals - visitorGoals > 0){
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == homeTeam
                        }?.points = homeTeamPoints + Match.WIN_POINTS
                    } else if (homeGoals - visitorGoals == 0){
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == homeTeam
                        }?.points = homeTeamPoints + Match.TIE_POINTS
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == visitorTeam
                        }?.points = visitorTeamPoints + Match.TIE_POINTS
                    } else{
                        leagueTournamentViewModel.listPlayers.find {
                            it.team.name == visitorTeam
                        }?.points = visitorTeamPoints + Match.WIN_POINTS
                    }
                    leagueTournamentViewModel.updatePlayerListTable(leagueTournamentViewModel.listPlayers)
                }
            }

            override fun afterTextChanged(s: Editable?){}

        })


    }

    override fun getItemCount() = matches.size
}