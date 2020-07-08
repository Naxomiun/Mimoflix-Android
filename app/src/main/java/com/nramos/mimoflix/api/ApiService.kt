package com.nramos.mimoflix.api

import com.nramos.mimoflix.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&language=es-ES")
    suspend fun getNowPlayingMovies() : Response<MovieApiResponse>

    @GET("movie/popular?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&language=es-ES")
    suspend fun getPopularMovies() : Response<MovieApiResponse>

    @GET("movie/top_rated?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&language=es-ES")
    suspend fun getTopRatedMovies() : Response<MovieApiResponse>

    @GET("movie/upcoming?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&language=es-ES")
    suspend fun getUpcomingMovies() : Response<MovieApiResponse>

    @GET("search/movie?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") query: String) : Response<MovieApiResponse>

    @GET("movie/popular?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&language=es-ES&page=1")
    suspend fun getMoviesPerCompany(@Query("with_companies") id: Int) : Response<MovieApiResponse>

    @GET("trending/movie/day?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&include_adult=false")
    suspend fun getTredingMovies() : Response<MovieApiResponse>

    @GET("movie/{id}/videos?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getTrailer(@Path(value = "id") id: Int) : Response<TrailerApiResponse>

    @GET("discover/movie?api_key=7ea20134f2a81fb24515d4af532cfe46&sort_by=popularity.desc&include_adult=false&include_video=false&language=es-ES&page=1")
    suspend fun getMoviesPerGenre(@Query("with_genres") id: Int) : Response<MovieApiResponse>

    @GET("movie/{id}?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&append_to_response=credits")
    suspend fun getMovieDetail(@Path(value = "id") id: Int) : Response<MovieDetail>

    @GET("movie/{id}/similar?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getRelatedMovies(@Path(value = "id") id: Int) : Response<MovieApiResponse>

    @GET("movie/top_rated?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES&page=1")
    suspend fun getRecommendedMovies() : Response<MovieApiResponse>

    @GET("person/{id}?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getActorDetail(@Path(value = "id") id: Int) : Response<ActorDetail>

    @GET("person/{id}/movie_credits?api_key=7ea20134f2a81fb24515d4af532cfe46&language=es-ES")
    suspend fun getActorMovies(@Path(value = "id") id: Int) : Response<ActorMovies>

}