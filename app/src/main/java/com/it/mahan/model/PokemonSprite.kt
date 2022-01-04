package com.it.mahan.model

import com.google.gson.annotations.SerializedName

data class PokemonSprite(
    @SerializedName("back_default")
    val backImage: String,
    @SerializedName("front_default")
    val frontImage: String
)
