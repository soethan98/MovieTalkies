package com.soethan.movietalkies.ViewHolder

import android.view.View
import com.soethan.movietalkies.Utils.IMAGES_BASE_URL
import com.soethan.movietalkies.data.Vo.PopularVo
import com.soethan.movietalkies.delegate.MovieDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_content.view.*

class PopularViewHolder( itemView: View,private val mMovieDelegate: MovieDelegate):BaseViewHolder<PopularVo>(itemView) {


    private val posterCard = itemView.movieImageCard
    private val posterImage = itemView.movieImage
    private val movieRating = itemView.ratingCircle
    private val movieTitle = itemView.movie_title



    override fun bind(data: PopularVo) {

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