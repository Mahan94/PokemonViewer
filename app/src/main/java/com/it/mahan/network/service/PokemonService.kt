package com.it.mahan.network.service

import com.it.mahan.model.Pokemon
import com.it.mahan.model.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("v2/pokemon-species")
    suspend fun getPokemonSpecies(): Response<PokemonSpecies>

    @GET("v2/pokemon/{pokemon_id}")
    suspend fun getPokemonDetail(@Path("pokemon_id") id: Int): Response<Pokemon>
}