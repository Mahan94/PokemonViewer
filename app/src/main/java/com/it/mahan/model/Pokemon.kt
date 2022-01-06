package com.it.mahan.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("moves")
    val pokemonMoves: List<PokemonMove>,
    @SerializedName("sprites")
    val pokemonSprite: PokemonSprite,
    @SerializedName("stats")
    val pokemonStats: List<PokemonStat>
)