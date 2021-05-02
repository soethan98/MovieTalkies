package com.soethan.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.soethan.movietalkies.R
import com.soethan.movietalkies.ViewHolder.FavouriteViewHolder
import com.soethan.movietalkies.data.Vo.FavouriteVo
import com.soethan.movietalkies.delegate.MovieDelegate

class FavouriteRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<FavouriteViewHolder, FavouriteVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return FavouriteViewHolder(view,movieDelegate)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(mData!!.get(position))

    }
}