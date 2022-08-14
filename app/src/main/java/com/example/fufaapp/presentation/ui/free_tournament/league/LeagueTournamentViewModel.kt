package com.example.fufaapp.presentation.ui.free_tournament.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fufaapp.domain.model.Player
import com.example.fufaapp.domain.model.Team


class LeagueTournamentViewModel : ViewModel() {
    val listPlayers = mutableListOf<Player>()
    private var fixture = LeagueTournamentFixture()
    private var _playerList = MutableLiveData<List<Player>>()
    val playerList: LiveData<List<Player>>
        get() = _playerList
    var fixtureList = emptyList<List<String>>()

    fun setPlayer(playerName: String, teamName: String){
            val team = Team(teamName)
            val player = Player(position = listPlayers.size + 1, name = playerName, team = team)
            listPlayers.add(player)
            _playerList.postValue(listPlayers)
    }

    fun createFixture(){
        val teamList = listPlayers.map{ player ->
            player.team.name
        }
        this.fixtureList = this.fixture.createFixture(teamList)
    }


}