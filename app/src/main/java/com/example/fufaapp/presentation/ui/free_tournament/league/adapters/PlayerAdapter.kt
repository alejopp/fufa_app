package com.example.fufaapp.presentation.ui.free_tournament.league.adapters

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fufaapp.R
import com.example.fufaapp.databinding.ItemPlayerBinding
import com.example.fufaapp.domain.model.Player

class PlayerAdapter(private val playerList: List<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.binding.etvItemPlayerFullname.text = playerList[position].name
        holder.binding.etvItemPlayerTeam.text = playerList[position].team.name
    }

    override fun getItemCount() = playerList.size

    inner class PlayerViewHolder(val binding: ItemPlayerBinding): RecyclerView.ViewHolder(binding.root) {}

}

/*class PlayerAdapter(private val playerList: List<String>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.binding.etvItemPlayerFullname.text = playerList[position]
        holder.binding.etvItemPlayerTeam.text = playerList[position]
    }

    override fun getItemCount() = playerList.size

    inner class PlayerViewHolder(val binding: ItemPlayerBinding): RecyclerView.ViewHolder(binding.root) {}

}*/
