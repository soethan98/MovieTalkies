package com.soethan.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.soethan.movietalkies.R
import com.soethan.movietalkies.ViewHolder.PopularViewHolder
import com.soethan.movietalkies.data.Vo.PopularVo
import com.soethan.movietalkies.delegate.MovieDelegate

class PopularRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<PopularViewHolder, PopularVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return PopularViewHolder(view,movieDelegate)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}