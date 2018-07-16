package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.PopularViewHolder
import com.example.soe_than.movietalkies.ViewHolder.UpComingViewHolder
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.delegate.NowShowingDelegate
import com.example.soe_than.movietalkies.delegate.UpComingDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.UpComingViewModel

class UpComingRecyclerAdapter(val context: Context?, val upComingDelegate: UpComingDelegate) : BaseRecyclerAdapter<UpComingViewHolder, UpComingVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val view = mLayoutInflater.inflate(R.layout.movies_content, parent, false)

        return UpComingViewHolder(view,upComingDelegate)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}