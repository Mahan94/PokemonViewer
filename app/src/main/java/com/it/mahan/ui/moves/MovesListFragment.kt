package com.it.mahan.ui.moves

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.it.mahan.databinding.MovesListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class MovesListFragment : Fragment() {

    companion object {
        fun newInstance() = MovesListFragment()
        const val ARG_MOVES = "MovesListFragment_ARG_MOVES"
    }

    private val viewModel: MovesListViewModel by viewModels()

    private var _binding: MovesListFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("TAG", "onViewCreated: " + arguments)
        val moves = arguments?.getStringArrayList(ARG_MOVES)

        if (moves?.isNotEmpty() == true) {
            displayMoves(moves)
            binding.tvEmptyState.visibility = View.GONE
            binding.tvTitle.visibility = View.VISIBLE
        } else {
            binding.tvEmptyState.visibility = View.VISIBLE
            binding.tvTitle.visibility = View.GONE
        }

    }

    private fun displayMoves(moves: ArrayList<String>) {
        val layoutManager = GridLayoutManager(context, 2)

        binding.rvMoves.layoutManager = layoutManager

        binding.rvMoves.adapter = PokemonMoveAdapter(context!!, moves)
    }

}