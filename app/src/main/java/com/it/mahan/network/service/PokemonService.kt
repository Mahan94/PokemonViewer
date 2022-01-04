package com.it.mahan.network.service

import com.it.mahan.model.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("v2/pokemon-species")
    suspend fun getPokemonSpecies(): Response<PokemonSpecies>

}