package com.lubulwa.moftcinema.remote.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


data class MoftMovie(

    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genre_ids")
    val genreIds: ArrayList<Int>,

    val id: Int,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,

    val popularity: Double,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
) {

    companion object {

        @JvmStatic
        val DIFF_CALL: DiffUtil.ItemCallback<MoftMovie> =
            object : DiffUtil.ItemCallback<MoftMovie>() {
                override fun areItemsTheSame(oldItem: MoftMovie, newItem: MoftMovie): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: MoftMovie, newItem: MoftMovie): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}