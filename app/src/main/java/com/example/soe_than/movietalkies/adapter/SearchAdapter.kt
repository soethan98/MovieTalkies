package com.example.soe_than.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.SearchView
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.ViewHolder.PopularViewHolder
import com.example.soe_than.movietalkies.ViewHolder.SearchViewHolder
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate

class SearchAdapter(val context: Context?, val movieDelegate: MovieDelegate) : BaseRecyclerAdapter<SearchViewHolder, SearchVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = mLayoutInflater.inflate(R.layout.search_content, parent, false)

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}