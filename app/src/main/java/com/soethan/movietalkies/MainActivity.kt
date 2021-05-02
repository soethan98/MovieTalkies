package com.soethan.movietalkies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.soethan.movietalkies.adapter.ViewPagerAdapter
import com.soethan.movietalkies.ui.fragment.*
import com.soethan.movietalkies.ui.search.SearchActivity
import dagger.android.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),HasAndroidInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setSupportActionBar(toolbar)


        setUpViewPager(viewpager)

        tabLayout.setupWithViewPager(viewpager)

    }

    fun setUpViewPager(viewPager: androidx.viewpager.widget.ViewPager) {
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!!.itemId == R.id.action_search) {
            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        return true
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }


}
