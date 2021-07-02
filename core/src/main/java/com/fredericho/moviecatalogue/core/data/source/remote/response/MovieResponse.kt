package com.fredericho.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page : String,

    @SerializedName("results")
    val result : List<Movies>
)


