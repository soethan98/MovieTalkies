package com.soethan.movietalkies.ViewHolder

import android.view.View
import com.soethan.movietalkies.Utils.IMAGES_BASE_URL
import com.soethan.movietalkies.Utils.Utility
import com.soethan.movietalkies.data.Vo.SearchVo
import com.soethan.movietalkies.delegate.SearchDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_content.view.*

class SearchViewHolder( itemView: View,val mSearchDelegate: SearchDelegate):BaseViewHolder<SearchVo>(itemView) {
    override fun onClick(v: View?) {

    }

    private val movieLanguage = itemView.search_language
    private val posterImage = itemView.search_poster
    private val movieRating = itemView.search_rating
    private val movieTitle = itemView.search_title
    private val movieReleaseDate = itemView.search_releasedate
    private val movieGenres = itemView.genres_chip

    override fun bind(data: SearchVo) {
        movieLanguage.text = data.original_language.toUpperCase()
        movieRating.text = "${data.vote_average}"
        movieTitle.text = data.title
        movieReleaseDate.text = data.release_date
        Picasso.with(itemView.context).load("${IMAGES_BASE_URL + data.poster_path}").into(posterImage)

        movieGenres.setText(Utility.setGenresTypeForMovie(data.genreids))

        itemView.setOnClickListener {
            mSearchDelegate.onTapSearchResult(data)
        }


    }


}