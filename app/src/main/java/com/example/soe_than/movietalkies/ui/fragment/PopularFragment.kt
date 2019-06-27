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
import com.example.soe_than.movietalkies.adapter.PopularRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MovieViewModelFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_popular.view.*


class PopularFragment : Fragment(), MovieDelegate {

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory
    lateinit var popularAdapter: PopularRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popular, container, false)
        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getPopularMovies().observe(activity!!, Observer { popularList ->

            popularList!!.let {
                view.popProgress.visibility=View.GONE
                popularAdapter.setNewData(popularList as MutableList<PopularVo>)
            }
        })
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.popularRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        popularAdapter = PopularRecyclerAdapter(context!!, this)
        view.popularRecyclerView.adapter = popularAdapter
    }

    override fun onTapMovie(id: Int) {
        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "popular")
        startActivity(intent)
    }


}
