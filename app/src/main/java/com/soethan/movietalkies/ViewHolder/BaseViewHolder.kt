package com.soethan.movietalkies.ViewHolder

import android.view.View

abstract class BaseViewHolder<T>(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mDetechedFromWindow: Boolean = false

    init {





        itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                mDetechedFromWindow = false
            }

            override fun onViewDetachedFromWindow(v: View) {
                mDetechedFromWindow = true
            }
        })
    }

    abstract fun bind(data: T)



}