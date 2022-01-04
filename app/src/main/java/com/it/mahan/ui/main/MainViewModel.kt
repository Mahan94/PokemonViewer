package com.it.mahan.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.it.mahan.data.PokemonRepository
import com.it.mahan.model.MyResult
import com.it.mahan.model.PokemonSpecies
import com.it.mahan.util.Logging.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    private val _pokemonSpecies = MutableLiveData<MyResult<PokemonSpecies>>()
    val pokemonSpecies = _pokemonSpecies

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _snackBar = MutableLiveData<String?>()
    val snackBar: LiveData<String?>
        get() = _snackBar

    fun onSnackBarShown() {
        _snackBar.value = null
    }

    private var pokemonsTotalCount = 0

//    init {
//        fetchPokemonSpecies()
//    }

    fun displayRandomPokemon() {

        viewModelScope.launch {
            try {
                _spinner.value = true

                if (pokemonsTotalCount == 0) {
                    fetchPokemonSpecies()
                }

                val randomPokemonId = (1..pokemonsTotalCount).random()
                fetchPokemonDetail(randomPokemonId)

            } catch (error: Exception) {
                _snackBar.value = error.message
            } finally {
                _spinner.value = false
            }
        }

    }

    private suspend fun fetchPokemonDetail(id: Int) {
        pokemonRepository.fetchPokemonDetail(id).collect {
            when (it?.status) {
                MyResult.Status.SUCCESS -> {
                    Log.e(TAG, "fetchPokemonDetail: ${it.data.toString()}" )
//                    _pokemonSpecies.value = it
                }
                MyResult.Status.ERROR -> {
                    throw Exception(it.message)
                }
                MyResult.Status.LOADING -> {
                }
                else -> {
                }
            }
        }
    }

    private suspend fun fetchPokemonSpecies() {
        pokemonRepository.fetchPokemonSpecies().collect {
            when (it?.status) {
                MyResult.Status.SUCCESS -> {
                    pokemonsTotalCount = it.data?.count ?: 0
                    _pokemonSpecies.value = it
                }
                MyResult.Status.ERROR -> {
                    throw Exception(it.message)
                }
                MyResult.Status.LOADING -> {
                }
                else -> {
                }
            }
        }
    }

    private fun launchDataLoad(block: suspend (MyResult<PokemonSpecies>?) -> Unit): Unit {

        viewModelScope.launch {
            try {
                _spinner.value = true

                pokemonRepository.fetchPokemonSpecies().collect {
                    when (it?.status) {
                        MyResult.Status.LOADING -> {
                        }
                        MyResult.Status.SUCCESS -> {
                            block(it)
                        }
                        MyResult.Status.ERROR -> {
                            _snackBar.value = it.message
                        }
                        else -> {
                        }
                    }
                }
            } catch (error: Exception) {
            } finally {
                _spinner.value = false
            }
        }
    }
}