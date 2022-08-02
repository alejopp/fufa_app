package com.example.fufaapp.presentation.ui.free_tournament.league

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fufaapp.domain.model.Player
import com.example.fufaapp.domain.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConfigViewModel : ViewModel() {
    private val listPlayers = mutableListOf<Player>()
    private var _playerList = MutableLiveData<List<Player>>()
    val playerList: LiveData<List<Player>>
        get() = _playerList

    init {
        Log.i("vm", "viewmodel created")
    }

    fun setPlayer(playerName: String, teamName: String){
            val team = Team(teamName)
            val player = Player(name = playerName, team = team)
            listPlayers.add(player)
            _playerList.postValue(listPlayers)
    }
}