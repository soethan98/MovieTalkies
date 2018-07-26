package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.NowShowingViewHolder
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate

class NowShowingRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<NowShowingViewHolder, NowShowingVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return NowShowingViewHolder(view,movieDelegate)
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}