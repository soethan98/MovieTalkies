package com.example.soe_than.movietalkies.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.adapter.FavouriteRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.FavouriteViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.FavouriteViewFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favourite.view.*


class FavouriteFragment : Fragment(), MovieDelegate {
    override fun onTapMovie(id: Int) {
        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "favourite")
        startActivity(intent)
    }

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var viewModelFactory: FavouriteViewFactory
    private lateinit var favouriteAdapter: FavouriteRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        viewModelFactory = InjectorUtils.provideFavouriteViewFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavouriteViewModel::class.java)

        setUpRecyclerView(view)

        getFavouriteMovies()




        return view
    }

    private fun setUpRecyclerView(view: View) {


        view.favouriteRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        favouriteAdapter = FavouriteRecyclerAdapter(context!!, this)
        view.favouriteRecyclerView.adapter = favouriteAdapter
    }

    private fun getFavouriteMovies() {

        viewModel.getFavouriteMovies().observe(activity!!, Observer { favouriteList ->

            favouriteList!!.let {

                favouriteAdapter.setNewData(favouriteList as MutableList<FavouriteVo>)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getFavouriteMovies()
    }


}
