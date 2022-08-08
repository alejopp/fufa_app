package com.example.fufaapp.presentation.ui.free_tournament.league

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.fufaapp.R
import com.example.fufaapp.databinding.FragmentLeagueTournamentBinding


class LeagueTournamentFragment : Fragment() {

    private val configViewModel: ConfigViewModel by activityViewModels()
    private var _binding: FragmentLeagueTournamentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueTournamentBinding.inflate(inflater,container,false)
            for(i in 0 until configViewModel.listPlayers.size){
                Log.i("Player", "Player $i")
                val tr = TableRow(requireContext())
                for (j in 0..5){
                    Log.i("Item", "Item $j")
                    val tv = TextView(requireContext())
                    when(j){
                        0 -> tv.text = configViewModel.listPlayers[i].position.toString()
                        1 -> tv.text = configViewModel.listPlayers[i].team.name
                        2 -> tv.text = configViewModel.listPlayers[i].goalsFor.toString()
                        3 -> tv.text = configViewModel.listPlayers[i].goalsAgainst.toString()
                        4 -> tv.text = configViewModel.listPlayers[i].goalDifference.toString()
                        5 -> tv.text = configViewModel.listPlayers[i].goalDifference.toString()
                    }
                    tr.addView(tv)
                }
                binding.root.addView(tr)
            }

/*        val tv1 = TextView(requireContext())
        tv1.text = "1"
        val tv2 = TextView(requireContext())
        tv2.text = "Liverpool"
        val tv3 = TextView(requireContext())
        tv3.text = "3"
        val tv4 = TextView(requireContext())
        tv4.text = "0"
        val tv5 = TextView(requireContext())
        tv5.text = "2"
        val tv6 = TextView(requireContext())
        tv6.text = "3"
        val trow = TableRow(requireContext())
        trow.addView(tv1)
        trow.addView(tv2)
        trow.addView(tv3)
        trow.addView(tv4)
        trow.addView(tv5)
        trow.addView(tv6)


        val t1 = TextView(requireContext())
        t1.text = "2"
        val t2 = TextView(requireContext())
        t2.text = "Manchester United"
        val t3 = TextView(requireContext())
        t3.text = "2"
        val t4 = TextView(requireContext())
        t4.text = "2"
        val t5 = TextView(requireContext())
        t5.text = "0"
        val t6 = TextView(requireContext())
        t6.text = "1"
        val trow2 = TableRow(requireContext())
        trow2.addView(t1)
        trow2.addView(t2)
        trow2.addView(t3)
        trow2.addView(t4)
        trow2.addView(t5)
        trow2.addView(t6)
        binding.root.addView(trow)
        binding.root.addView(trow2)*/

        return binding.root
    }



}