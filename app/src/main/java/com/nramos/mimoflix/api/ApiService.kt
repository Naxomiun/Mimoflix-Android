package com.nramos.mimoflix.api

import com.nramos.mimoflix.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") query: String) : Response<MovieApiResponse>

    @GET("trending/movie/day?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&include_adult=false")
    suspend fun getTredingMovies() : Response<MovieApiResponse>

    @GET("movie/{id}/videos?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getTrailer(@Path(value = "id") id: Int) : Response<TrailerApiResponse>

    @GET("movie/popular?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&language=es-ES&page=1")
    suspend fun getPopularMovies() : Response<MovieApiResponse>

    @GET("discover/movie?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMoviesPerGenre(@Query("with_genres") id: Int) : Response<MovieApiResponse>

    @GET("discover/tv?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&language=es-ES&page=1")
    suspend fun getSeriesPerGenre(@Query("with_genres") id: Int) : Response<SerieApiResponse>

    @GET("genre/movie/list?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getMovieGenres() : Response<GenreApiResponse>

    @GET("genre/tv/list?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getTvGenres() : Response<GenreApiResponse>

    @GET("movie/{id}?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&append_to_response=credits")
    suspend fun getMovieDetail(@Path(value = "id") id: Int) : Response<MovieDetail>

    @GET("movie/{id}/similar?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getRelatedMovies(@Path(value = "id") id: Int) : Response<MovieApiResponse>

    @GET("movie/{id}/images?api_key=7ea20134f2a81fb24515d4af532cfe46")
    suspend fun getMovieImages(@Path(value = "id") id: Int) : Response<ImageResponse>

    @GET("movie/top_rated?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&page=1")
    suspend fun getRecommendedMovies() : Response<MovieApiResponse>

    @GET("tv/top_rated?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&page=1")
    suspend fun getRecommendedSeries() : Response<SerieApiResponse>

}