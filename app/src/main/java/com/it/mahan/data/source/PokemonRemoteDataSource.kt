package com.it.mahan.data.source

import com.it.mahan.model.PokemonSpecies
import com.it.mahan.network.service.PokemonService
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import com.it.mahan.model.MyResult
import com.it.mahan.util.ErrorUtils

class PokemonRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchPokemonSpecies(): MyResult<PokemonSpecies> {
        val pokemonService = retrofit.create(PokemonService::class.java)

        return getResponse(
            request = { pokemonService.getPokemonSpecies() },
            defaultErrorMessage = "Error fetching Pokemon species"
        )
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): MyResult<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return MyResult.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                MyResult.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            MyResult.error(e.localizedMessage?:"Unknown Error", null)
        }
    }
}