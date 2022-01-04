package com.it.mahan.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.it.mahan.R
import com.it.mahan.databinding.MainFragmentBinding
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
        viewModel.pokemonSpecies.observe(this) { value ->
            value.let {
                binding.message.text = value.data?.count?.toString() ?: "No Pokemon Found"
            }
        }

        binding.message.setOnClickListener() {
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
    }

}