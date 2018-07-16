package com.example.soe_than.movietalkies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.soe_than.movietalkies.adapter.ViewPagerAdapter
import com.example.soe_than.movietalkies.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)


        setUpViewPager(viewpager)

        tabLayout.setupWithViewPager(viewpager)
    }

    fun setUpViewPager(viewPager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(NowShowingFragment(), "NowShowing Movies")
        adapter.addFrag(UpcomingFragment(), "Upcoming Movies")
        adapter.addFrag(PopularFragment(), "Popular Movies")
        adapter.addFrag(TopRatedFragment(), "TopRated Movies")
        adapter.addFrag(FavouriteFragment(), "Favourite Movies")

        viewPager.adapter = adapter

    }
}
