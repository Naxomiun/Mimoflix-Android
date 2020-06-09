package com.nramos.mimoflix.repo

import com.nramos.mimoflix.api.ApiService
import com.nramos.mimoflix.api.RecommendedProvider
import com.nramos.mimoflix.model.Serie

class SeriesRepository(
    private val apiService: ApiService,
    private val recommendedProvider: RecommendedProvider
) : BaseRepository(){


    suspend fun getRecommendedSeries() : List<Serie>? {
        return seriesSafeCall {
            apiService.getRecommendedSeries()
        }
    }

}
