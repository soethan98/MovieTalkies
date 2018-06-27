package com.example.soe_than.movietalkies.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.nio.file.Files.size


class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    val mFragmentList = arrayListOf<Fragment>()
    val mFragmentTitleList = arrayListOf<String>()


    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }
}