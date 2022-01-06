package com.it.mahan.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.it.mahan.databinding.MainFragmentBinding
import com.it.mahan.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setObservers()
    }

    private fun setObservers() {

        binding.btnNewPokemon.setOnClickListener() {
            viewModel.displayRandomPokemon()
        }

        viewModel.spinner.observe(this) { value ->
            value.let { show ->
                binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        viewModel.snackBar.observe(this) { text ->
            text?.let {
                Snackbar.make(binding.main, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackBarShown()
            }
        }

        viewModel.displayedPokemon.observe(this) { pokemon ->
            pokemon?.let {
                displayPokemon(pokemon)
            }

        }
    }

    private fun displayPokemon(pokemon: Pokemon) {

        pokemon.pokemonSprite.let {
            Log.e("TAG", "displayPokemon: " + it.frontImage)
            binding.ivFront.load(it.frontImage) {
                crossfade(true)
//                placeholder(R.drawable.image)
                transformations(CircleCropTransformation())
            }

            binding.ivBack.load(it.backImage) {
                crossfade(true)
//                placeholder(R.drawable.image)
                transformations(CircleCropTransformation())
            }
        }

        binding.tvName.text = pokemon.name

        displayMoves(pokemon)
        displayStats(pokemon)

    }

    private fun displayMoves(pokemon: Pokemon) {
        val moves = pokemon.pokemonMoves.map { pm -> pm.move.name }
        val layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(
            context,
            layoutManager.orientation
        )
        binding.rvMoves.layoutManager = layoutManager
        binding.rvMoves.addItemDecoration(dividerItemDecoration)
        binding.rvMoves.adapter = PokemonMoveAdapter(context!!, moves)
    }

    private fun displayStats(pokemon: Pokemon) {
//        val stats = pokemon.pokemonStats
//        val layoutManager = LinearLayoutManager(context)
//
//        val dividerItemDecoration = DividerItemDecoration(
//            context,
//            layoutManager.orientation
//        )
//        binding.rvMoves.layoutManager = layoutManager
//        binding.rvMoves.addItemDecoration(dividerItemDecoration)
//        binding.rvMoves.adapter = PokemonMoveAdapter(context!!, moves)
    }

}