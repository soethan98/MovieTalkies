package com.soethan.movietalkies.ui.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.soethan.movietalkies.R
import com.soethan.movietalkies.adapter.UpComingRecyclerAdapter
import com.soethan.movietalkies.data.Vo.UpComingVo
import com.soethan.movietalkies.delegate.MovieDelegate
import com.soethan.movietalkies.di.Injectable
import com.soethan.movietalkies.ui.ViewModel.MovieViewModel
import com.soethan.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import com.soethan.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_upcoming.view.*
import javax.inject.Inject


class UpcomingFragment : Fragment(), MovieDelegate, Injectable {
    override fun onTapMovie(id: Int) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "upcoming")
        startActivity(intent)
    }


    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var upComingAdapter: UpComingRecyclerAdapter




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
        setUpRecyclerView(view)

        viewModel.getUpComingMovies().observe(requireActivity(), Observer { upComingList ->

            upComingList!!.let {
                view.upProgress.visibility = View.GONE
                upComingAdapter.setNewData(upComingList as MutableList<UpComingVo>)
            }
        })
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.upcomingRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        upComingAdapter = UpComingRecyclerAdapter(requireContext(), this)
        view.upcomingRecyclerView.adapter = upComingAdapter
    }

    private fun getFavouriteMovies() {

    }


}
