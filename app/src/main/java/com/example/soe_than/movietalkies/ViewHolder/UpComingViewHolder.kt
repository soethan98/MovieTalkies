package com.example.soe_than.movietalkies.ViewHolder

import android.view.View
import com.bumptech.glide.Glide
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.delegate.NowShowingDelegate
import com.example.soe_than.movietalkies.delegate.UpComingDelegate
import kotlinx.android.synthetic.main.movies_content.view.*

class UpComingViewHolder(itemView: View, val mUpComingDelegate: UpComingDelegate) : BaseViewHolder<UpComingVo>(itemView) {

    private val posterImage = itemView.moviePoster


    override fun bind(data: UpComingVo) {
        Glide.with(itemView.context).load("${Constants.IMAGES_BASE_URL + data.posterPath}").into(posterImage)

        itemView.setOnClickListener(View.OnClickListener {
            mUpComingDelegate.onTapUpComingDelegate(data)
        })


    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}