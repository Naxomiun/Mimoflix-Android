package com.nramos.mimoflix.repo

import com.nramos.mimoflix.api.ApiService
import com.nramos.mimoflix.api.LocalProvider
import com.nramos.mimoflix.model.Serie
import com.nramos.mimoflix.repo.movies.BaseMovieRepository

class SeriesMovieRepository(
    private val apiService: ApiService,
    private val localProvider: LocalProvider
) : BaseMovieRepository(){


    suspend fun getRecommendedSeries() : List<Serie>? {
        return seriesSafeCall {
            apiService.getRecommendedSeries()
        }
    }

}
