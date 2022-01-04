package com.it.mahan.model

data class PokemonSpecies(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonSpeciesResult>
)