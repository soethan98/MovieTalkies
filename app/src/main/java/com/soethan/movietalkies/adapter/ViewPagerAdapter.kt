package com.soethan.movietalkies.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
    val mFragmentList = arrayListOf<androidx.fragment.app.Fragment>()
    val mFragmentTitleList = arrayListOf<String>()


    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: androidx.fragment.app.Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }
}