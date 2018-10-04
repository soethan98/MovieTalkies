package com.example.soe_than.movietalkies.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.adapter.UpComingRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MovieViewModelFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_upcoming.*
import kotlinx.android.synthetic.main.fragment_upcoming.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UpcomingFragment : Fragment(), MovieDelegate {
    override fun onTapMovie(id: Int) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "upcoming")
        startActivity(intent)
    }


    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory
    lateinit var upComingAdapter: UpComingRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)

        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
        setUpRecyclerView(view)

        viewModel.getUpComingMovies().observe(activity!!, Observer { upComingList ->

            upComingList!!.let {
              view.upProgress.visibility = View.GONE
                upComingAdapter.setNewData(upComingList as MutableList<UpComingVo>)
            }
        })
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.upcomingRecyclerView.layoutManager = GridLayoutManager(activity,2)
        upComingAdapter = UpComingRecyclerAdapter(context!!, this)
        view.upcomingRecyclerView.adapter = upComingAdapter
    }

    private fun getFavouriteMovies() {

    }


}
