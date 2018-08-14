package com.example.soe_than.movietalkies.ViewHolder

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_content.view.*

class UpComingViewHolder(itemView: View, val mMovieDelegate: MovieDelegate) : BaseViewHolder<UpComingVo>(itemView) {

    private val posterCard = itemView.movieImageCard
    private val posterImage = itemView.movieImage
    private val movieRating = itemView.ratingCircle
    private val movieTitle = itemView.movie_title


    override fun bind(data: UpComingVo) {
        Log.i("Hi",data.title + data.poster_path)

        Picasso.with(itemView.context).load("${Constants.IMAGES_BASE_URL + data.poster_path}").into(posterImage)
        movieRating.setText("${data.vote_average}")
        movieTitle.setText(data.title)

        posterCard.setOnClickListener(View.OnClickListener {
            mMovieDelegate.onTapMovie(data.id)
        })


    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}