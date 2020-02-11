package com.lubulwa.moftcinema.ui.movies.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lubulwa.moftcinema.R
import com.lubulwa.moftcinema.remote.model.MoftMovie
import javax.inject.Inject

class TrendingMoviesAdapter @Inject constructor() : RecyclerView.Adapter<TrendingMoviesAdapter.TMViewHolder>() {

    var movies: List<MoftMovie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TMViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.trending_movie_list_item, parent, false)

        return TMViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TMViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleText.text = movie.originalTitle
        holder.genresText.text = movie.genreIds.toString()
        holder.movieGenres.text = movie.voteAverage.toString()

        val requestOptions = RequestOptions()
            .apply(RequestOptions.centerCropTransform())
            .apply(RequestOptions.timeoutOf(6000))
            .override(200, 250)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(holder.itemView.context)
            .load(movie.posterPath)
            .apply(requestOptions)
            .into(holder.avatarImage)
    }

    class TMViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImage: ImageView = itemView.findViewById(R.id.image_avatar)
        var genresText: TextView = itemView.findViewById(R.id.text_genres)
        var titleText: TextView = itemView.findViewById(R.id.text_title)
        var movieGenres: TextView = itemView.findViewById(R.id.tv_rating)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}