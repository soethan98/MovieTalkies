package com.example.soe_than.movietalkies.ViewHolder

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.soe_than.movietalkies.Utils.IMAGES_BASE_URL
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_content.view.*

class UpComingViewHolder(itemView: View, private val mMovieDelegate: MovieDelegate) : BaseViewHolder<UpComingVo>(itemView) {

    private val posterCard = itemView.movieImageCard
    private val posterImage = itemView.movieImage
    private val movieRating = itemView.ratingCircle
    private val movieTitle = itemView.movie_title


    override fun bind(data: UpComingVo) {
        Log.i("Hi",data.title + data.posterPath)

        Picasso.with(itemView.context).load("${IMAGES_BASE_URL + data.posterPath}").into(posterImage)
        movieRating.text = "${data.voteAverage}"
        movieTitle.text = data.title

        posterCard.setOnClickListener {
            mMovieDelegate.onTapMovie(data.id)
        }


    }

    override fun onClick(v: View?) {

    }
}