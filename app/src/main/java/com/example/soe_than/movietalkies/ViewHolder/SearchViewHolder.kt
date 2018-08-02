package com.example.soe_than.movietalkies.ViewHolder

import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.view.View
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_content.view.*
import kotlinx.android.synthetic.main.search_content.view.*

class SearchViewHolder(itemView: View) : BaseViewHolder<SearchVo>(itemView) {
    override fun onClick(v: View?) {

    }

    private val movieLanguage = itemView.search_language
    private val posterImage = itemView.search_poster
    private val movieRating = itemView.search_rating
    private val movieTitle = itemView.search_title
    private val movieReleaseDate = itemView.search_releasedate

    override fun bind(data: SearchVo) {
        movieLanguage.setText(data.original_language.toUpperCase())
        movieRating.setText("${data.vote_average}")
        movieTitle.setText(data.title)
        movieReleaseDate.setText(data.release_date)
        Picasso.with(itemView.context).load("${Constants.IMAGES_BASE_URL + data.poster_path}").into(posterImage)

        Log.i("Search", "${data.genreids.size}")
    }


}