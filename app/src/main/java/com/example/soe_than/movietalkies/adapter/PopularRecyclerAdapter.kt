package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.NowShowingViewHolder
import com.example.soe_than.movietalkies.ViewHolder.PopularViewHolder
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.delegate.NowShowingDelegate
import com.example.soe_than.movietalkies.delegate.PopularDelegate

class PopularRecyclerAdapter(val context: Context?, val popularDelegate: PopularDelegate) : BaseRecyclerAdapter<PopularViewHolder, PopularVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return PopularViewHolder(view,popularDelegate)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}