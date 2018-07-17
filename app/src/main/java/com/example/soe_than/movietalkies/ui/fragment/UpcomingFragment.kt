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
import com.example.soe_than.movietalkies.adapter.UpComingRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.delegate.UpComingDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.UpComingViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.UpComingViewFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_now_showing.view.*
import kotlinx.android.synthetic.main.fragment_upcoming.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UpcomingFragment : Fragment(), UpComingDelegate {


    private lateinit var viewModel: UpComingViewModel
    private lateinit var viewModelFactory: UpComingViewFactory
    lateinit var upComingAdapter: UpComingRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)

        viewModelFactory = InjectorUtils.provideUpComingViewFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpComingViewModel::class.java)
        setUpRecyclerView(view)

        viewModel.getUpComingMovies().observe(activity!!, Observer { upComingList -> upComingAdapter.setNewData(upComingList as MutableList<UpComingVo>) })
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.upcomingRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        upComingAdapter = UpComingRecyclerAdapter(context!!,this)
        view.upcomingRecyclerView.adapter = upComingAdapter
    }

    override fun onTapUpComingDelegate(upComingVo: UpComingVo) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("MOVIE", upComingVo)
        startActivity(intent)
    }


}
