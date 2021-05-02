package com.soethan.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.soethan.movietalkies.R
import com.soethan.movietalkies.ViewHolder.NowShowingViewHolder
import com.soethan.movietalkies.data.Vo.NowShowingVo
import com.soethan.movietalkies.delegate.MovieDelegate

class NowShowingRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<NowShowingViewHolder, NowShowingVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return NowShowingViewHolder(view,movieDelegate)
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}