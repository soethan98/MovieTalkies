package com.soethan.movietalkies.ui.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.soethan.movietalkies.R
import com.soethan.movietalkies.adapter.TopRatedRecyclerAdapter
import com.soethan.movietalkies.data.Vo.TopRatedVo
import com.soethan.movietalkies.delegate.MovieDelegate
import com.soethan.movietalkies.di.Injectable
import com.soethan.movietalkies.ui.ViewModel.MovieViewModel
import com.soethan.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import com.soethan.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_top_rated.view.*
import javax.inject.Inject


class TopRatedFragment : Fragment(), MovieDelegate,Injectable{
    override fun onTapMovie(id: Int) {

        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "toprated")
        startActivity(intent)
    }


    private lateinit var viewModel: MovieViewModel

    @Inject
     lateinit var viewModelFactory: MainViewModelFactory
    lateinit var topRatedAdapter: TopRatedRecyclerAdapter




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_rated, container, false)

//        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getTopRatedMovies().observe(activity!!, Observer { topRatedList ->


            topRatedList!!.let {
                view.topProgress.visibility = View.GONE

                topRatedAdapter.setNewData(topRatedList as MutableList<TopRatedVo>)
            }
        });


        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.topRatedRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        topRatedAdapter = TopRatedRecyclerAdapter(context!!, this)
        view.topRatedRecyclerView.adapter = topRatedAdapter
    }


}
