package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.TopRatedViewHolder
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate

class TopRatedRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<TopRatedViewHolder, TopRatedVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return TopRatedViewHolder(view, movieDelegate)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}
