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
import com.example.soe_than.movietalkies.adapter.TopRatedRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.TopRatedViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.TopRatedViewFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_top_rated.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedFragment : Fragment(), MovieDelegate {
    override fun onTapMovie(id: Int) {

        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "toprated")
        startActivity(intent)
    }



    private lateinit var viewModel: TopRatedViewModel
    private lateinit var viewModelFactory: TopRatedViewFactory
    lateinit var topRatedAdapter: TopRatedRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_rated, container, false)

        viewModelFactory = InjectorUtils.provideTopRatedViewFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopRatedViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getTopRatedMovies().observe(activity!!, Observer { topRatedList ->

            if (topRatedList!!.size != 0 && topRatedList != null) {
                view.topProgress.visibility = View.GONE

                topRatedAdapter.setNewData(topRatedList as MutableList<TopRatedVo>)


            }
        });


        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.topRatedRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        topRatedAdapter = TopRatedRecyclerAdapter(context!!, this)
        view.topRatedRecyclerView.adapter = topRatedAdapter
    }


}