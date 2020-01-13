package com.lubulwa.moftcinema.remote.model


import com.google.gson.annotations.SerializedName
import com.lubulwa.moftcinema.remote.model.MoftMovie

data class MovieResponse(

    val page: Int,

    @SerializedName("results")
    val moftMovies: List<MoftMovie>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)