package com.example.fufaapp.presentation.ui.free_tournament.league.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fufaapp.databinding.ItemMatchBinding

class MatchAdapter(private val matches: List<List<String>>): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    inner class MatchViewHolder(val binding: ItemMatchBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(ItemMatchBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.binding.tvHt.text = matches[position][0]
        holder.binding.tvVt.text = matches[position][1]
    }

    override fun getItemCount() = matches.size
}