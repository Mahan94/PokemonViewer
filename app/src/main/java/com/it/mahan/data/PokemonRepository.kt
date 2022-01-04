package com.it.mahan.data

import com.it.mahan.data.source.PokemonRemoteDataSource
import com.it.mahan.model.PokemonSpecies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

import com.it.mahan.model.MyResult
import com.it.mahan.model.Pokemon
import kotlinx.coroutines.flow.flowOn

class PokemonRepository @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
) {

    suspend fun fetchPokemonSpecies(): Flow<MyResult<PokemonSpecies>?> {
        return flow {
            emit(MyResult.loading())
            val result = pokemonRemoteDataSource.fetchPokemonSpecies()

            // TODO: 1/4/2022 cache

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchPokemonDetail(id: Int): Flow<MyResult<Pokemon>?> {
        return flow {
            emit(MyResult.loading())
            val result = pokemonRemoteDataSource.getPokemonDetail(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}