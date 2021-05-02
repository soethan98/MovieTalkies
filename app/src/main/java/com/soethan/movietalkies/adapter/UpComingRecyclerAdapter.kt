package com.soethan.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.soethan.movietalkies.R
import com.soethan.movietalkies.ViewHolder.UpComingViewHolder
import com.soethan.movietalkies.data.Vo.UpComingVo
import com.soethan.movietalkies.delegate.MovieDelegate

class UpComingRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<UpComingViewHolder, UpComingVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return UpComingViewHolder(view, movieDelegate)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}