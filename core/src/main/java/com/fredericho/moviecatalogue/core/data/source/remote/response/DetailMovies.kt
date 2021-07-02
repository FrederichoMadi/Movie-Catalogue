package com.fredericho.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovies (
        @SerializedName("id")
        val id : Int,

        @SerializedName("title")
        val title : String,

        @SerializedName("overview")
        val overview : String,

        @SerializedName("release_date")
        val date : String,

        @SerializedName("poster_path")
        val poster : String,

        @SerializedName("vote_average")
        val rating : Double,

        @SerializedName("genres")
        val genres : List<Genre>
)