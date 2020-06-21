package com.example.soe_than.movietalkies.ViewHolder

import android.view.View
import com.example.soe_than.movietalkies.Utils.IMAGES_BASE_URL
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_content.view.*

class NowShowingViewHolder(itemView: View, private val mMovieDelegate: MovieDelegate) : BaseViewHolder<NowShowingVo>(itemView) {
    override fun onClick(v: View?) {
    }

    private val posterCard = itemView.movieImageCard
    private val posterImage = itemView.movieImage
    private val movieRating = itemView.ratingCircle
    private val movieTitle = itemView.movie_title

    override fun bind(data: NowShowingVo) {
        Picasso.with(itemView.context).load("${IMAGES_BASE_URL + data.posterPath}").into(posterImage)
        movieRating.text = "${data.voteAverage}"
        movieTitle.text = data.title

        posterCard.setOnClickListener {
            mMovieDelegate.onTapMovie(data.id)
        }
    }
}
