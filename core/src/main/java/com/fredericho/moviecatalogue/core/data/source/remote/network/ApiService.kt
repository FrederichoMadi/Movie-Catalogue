package com.fredericho.moviecatalogue.core.data.source.remote.network

import com.fredericho.moviecatalogue.core.data.source.remote.response.DetailMovies
import com.fredericho.moviecatalogue.core.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") api_key : String = "1fcef99d7c757dfe6309944658d887a8",
        @Query("page") page : String = "1",
        @Query("language") language : String = "en-US",
    ) : Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id : Int,
        @Query("api_key") api_key: String = "1fcef99d7c757dfe6309944658d887a8"
    ) : Call<DetailMovies>


}