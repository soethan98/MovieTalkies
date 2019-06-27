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
import com.example.soe_than.movietalkies.adapter.NowShowingRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MovieViewModelFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_now_showing.view.*



class NowShowingFragment : Fragment(), MovieDelegate {


    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory
    lateinit var nowShowingAdapter: NowShowingRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_now_showing, container, false)

        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getNowShowingMovies().observe(activity!!,
                Observer { nowshowingList ->


                    nowshowingList!!.let {
                        view.nowProgress.visibility = View.GONE

                        nowShowingAdapter.setNewData(nowshowingList as MutableList<NowShowingVo>)

                    }
                })
        return view
    }

    private fun setUpRecyclerView(view: View) {

        view.nowShowingRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        nowShowingAdapter = NowShowingRecyclerAdapter(context!!, this)
        view.nowShowingRecyclerView.adapter = nowShowingAdapter
    }



    override fun onTapMovie(id: Int) {

        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "nowshowing")
        startActivity(intent)

    }


}
