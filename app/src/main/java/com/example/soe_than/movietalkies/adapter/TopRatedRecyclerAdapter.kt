package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.PopularViewHolder
import com.example.soe_than.movietalkies.ViewHolder.TopRatedViewHolder
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.delegate.NowShowingDelegate
import com.example.soe_than.movietalkies.delegate.TopRatedDelegate

class TopRatedRecyclerAdapter(val context: Context?, val topRatedDelegate: TopRatedDelegate) : BaseRecyclerAdapter<TopRatedViewHolder, TopRatedVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return TopRatedViewHolder(view,topRatedDelegate)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}