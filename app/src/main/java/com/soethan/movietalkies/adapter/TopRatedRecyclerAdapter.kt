package com.soethan.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.soethan.movietalkies.R
import com.soethan.movietalkies.ViewHolder.TopRatedViewHolder
import com.soethan.movietalkies.data.Vo.TopRatedVo
import com.soethan.movietalkies.delegate.MovieDelegate

class TopRatedRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<TopRatedViewHolder, TopRatedVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return TopRatedViewHolder(view,movieDelegate)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}