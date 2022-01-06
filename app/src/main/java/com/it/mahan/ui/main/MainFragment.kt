package com.it.mahan.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.it.mahan.databinding.MainFragmentBinding
import com.it.mahan.model.Pokemon
import com.it.mahan.ui.moves.MovesListFragment
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
                binding.btnNewPokemon.isEnabled = !show
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

//        displayMoves(pokemon)
//        displayStats(pokemon)


        val moves = ArrayList(pokemon.pokemonMoves.map { pm -> pm.move.name })
        val fragmentList = arrayListOf<Fragment>(
            MovesListFragment().apply {
                arguments =
                    Bundle().apply { putStringArrayList(MovesListFragment.ARG_MOVES, moves) }
            },
            MovesListFragment()
        )

        binding.pager.adapter = MoveStatAdapter(this, pokemon, fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
        }.attach()
    }

    class MoveStatAdapter(
        fragment: Fragment,
        private val pokemon: Pokemon,
        private val fragments: ArrayList<Fragment>
    ) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment {
//            // Return a NEW fragment instance in createFragment(int)
//            var fragment: Fragment
//            if (position == 0) {
//                fragment = MovesListFragment()
//
//                fragment.arguments = Bundle().apply {
////                    putInt(ARG_OBJECT, position + 1)
//                    val moves = pokemon.pokemonMoves.map { pm -> pm.move.name }
//                    putParcelable("pokemon", moves)
//                }
//            } else {
//                fragment = MovesListFragment()
//            }
//
////            fragment.arguments = Bundle().apply {
////                // Our object is just an integer :-P
////                putInt(ARG_OBJECT, position + 1)
////            }
//            return fragment
            return fragments[position]
        }
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