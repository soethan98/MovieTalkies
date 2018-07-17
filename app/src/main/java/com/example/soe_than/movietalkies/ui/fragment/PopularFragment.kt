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
import com.example.soe_than.movietalkies.adapter.PopularRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.delegate.PopularDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.PopularViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.PopularViewFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_now_showing.view.*
import kotlinx.android.synthetic.main.fragment_popular.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PopularFragment : Fragment(),PopularDelegate {
    override fun onTapPopular(popularVo: PopularVo) {
        var intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("MOVIE",popularVo)
        startActivity(intent)
    }

    private lateinit var viewModel: PopularViewModel
    private lateinit var viewModelFactory: PopularViewFactory
    lateinit var popularAdapter: PopularRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popular, container, false)
        viewModelFactory = InjectorUtils.providePopularViewFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PopularViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getPopularMovies().observe(activity!!, Observer { popularList->popularAdapter.setNewData(popularList as MutableList<PopularVo>) })
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.popularRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        popularAdapter = PopularRecyclerAdapter(context!!,this)
        view.popularRecyclerView.adapter = popularAdapter
    }


}
