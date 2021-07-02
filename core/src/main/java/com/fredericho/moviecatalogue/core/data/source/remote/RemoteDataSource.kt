package com.fredericho.moviecatalogue.core.data.source.remote

import android.util.Log
import com.fredericho.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.fredericho.moviecatalogue.core.data.source.remote.network.ApiService
import com.fredericho.moviecatalogue.core.data.source.remote.response.DetailMovies
import com.fredericho.moviecatalogue.core.data.source.remote.response.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.*

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies() : Flow<ApiResponse<List<Movies>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.awaitResponse().body()?.result!!
                Log.d("remotedatasource", dataArray.toString())
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                e.printStackTrace()
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovies(movieId : Int) : Flow<ApiResponse<DetailMovies>>{
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId)
                val dataArray = response.awaitResponse().body()
                if (dataArray != null){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                e.printStackTrace()
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}