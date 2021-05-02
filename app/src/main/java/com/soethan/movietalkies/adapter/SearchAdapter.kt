package com.soethan.movietalkies.adapter

import android.content.Context
import android.view.ViewGroup
import com.soethan.movietalkies.R
import com.soethan.movietalkies.ViewHolder.SearchViewHolder
import com.soethan.movietalkies.data.Vo.SearchVo
import com.soethan.movietalkies.delegate.SearchDelegate

class SearchAdapter(val context: Context?, val searchDelegate: SearchDelegate) : BaseRecyclerAdapter<SearchViewHolder, SearchVo>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = mLayoutInflater.inflate(R.layout.search_content, parent, false)

        return SearchViewHolder(view,searchDelegate)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }
}