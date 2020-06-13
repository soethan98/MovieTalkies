package com.example.soe_than.movietalkies.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.example.soe_than.movietalkies.ViewHolder.BaseViewHolder


import java.util.ArrayList

abstract class BaseRecyclerAdapter<T : BaseViewHolder<*>, W>(context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<T>() {

    protected var mLayoutInflater: LayoutInflater

    protected var mData: MutableList<W>? = null

    val items: List<W>
        get() = if (mData == null) ArrayList() else mData!!

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mData = ArrayList()
    }

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }

    fun setNewData(newData: MutableList<W>) {
        mData = newData
        notifyDataSetChanged()
    }

    fun appendNewData(newData: List<W>) {
        mData!!.addAll(newData)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): W? {
        return if (position < mData!!.size - 1) mData!![position] else null

    }

    fun removeData(data: W) {
        mData!!.remove(data)
        notifyDataSetChanged()
    }

    fun addNewData(data: W) {
        mData!!.add(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData = ArrayList()
        notifyDataSetChanged()
    }
}