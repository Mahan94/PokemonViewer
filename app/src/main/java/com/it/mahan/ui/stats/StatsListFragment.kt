package com.it.mahan.ui.stats

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.it.mahan.R
import com.it.mahan.databinding.MovesListFragmentBinding
import com.it.mahan.databinding.StatsListFragmentBinding
import com.it.mahan.model.PokemonStat
import com.it.mahan.ui.moves.MovesListViewModel
import com.it.mahan.ui.moves.PokemonMoveAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class StatsListFragment : Fragment() {

        companion object {
            fun newInstance() = StatsListFragment()
            const val ARG_STATS = "StatsListFragment_ARG_STATS"
        }

        private val viewModel: StatsListViewModel by viewModels()

        private var _binding: StatsListFragmentBinding? = null
        private val binding get() = _binding!!


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = StatsListFragmentBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            Log.e("TAG", "onViewCreated: " + arguments)

            val stats = arguments?.getParcelableArrayList<PokemonStat>(ARG_STATS)

            if (stats?.isNotEmpty() == true) {
                displayStats(stats)
                binding.tvEmptyState.visibility = View.GONE
                binding.tvTitle.visibility = View.VISIBLE
            } else {
                binding.tvEmptyState.visibility = View.VISIBLE
                binding.tvTitle.visibility = View.GONE
            }

        }

    private fun displayStats(stats: ArrayList<PokemonStat>) {
            val layoutManager = GridLayoutManager(context, 2)

            binding.rvMoves.layoutManager = layoutManager

            binding.rvMoves.adapter = PokemonStatAdapter(context!!, stats)
        }

    }