package com.example.soe_than.movietalkies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.example.soe_than.movietalkies.adapter.ViewPagerAdapter
import com.example.soe_than.movietalkies.ui.fragment.*
import com.example.soe_than.movietalkies.ui.search.SearchActivity
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_search) {
            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        return true
    }


}
