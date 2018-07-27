package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.FavouriteViewHolder
import com.example.soe_than.movietalkies.ViewHolder.NowShowingViewHolder
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate

class FavouriteRecyclerAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<FavouriteViewHolder, FavouriteVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return FavouriteViewHolder(view,movieDelegate)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(mData!!.get(position))

    }
}