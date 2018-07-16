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
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants.LOG_TAG
import com.example.soe_than.movietalkies.adapter.NowShowingRecyclerAdapter
import com.example.soe_than.movietalkies.adapter.PopularRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.NowShowingDelegate
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_now_showing.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NowShowingFragment : Fragment(), NowShowingDelegate {


    private lateinit var viewModel: NowShowingViewModel
    private lateinit var viewModelFactory: NowShowingViewFactory
    lateinit var nowShowingAdapter: NowShowingRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_now_showing, container, false)

        viewModelFactory = InjectorUtils.provideNowShowingViewFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NowShowingViewModel::class.java)

        setUpRecyclerView(view)


        viewModel.getNowShowingMovies().observe(activity!!,
                Observer { t -> nowShowingAdapter.setNewData(t as MutableList<NowShowingVo>) })
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.nowShowingRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        nowShowingAdapter = NowShowingRecyclerAdapter(context!!,this)
        view.nowShowingRecyclerView.adapter = nowShowingAdapter
    }

    override fun onTapNowShowing(nowShowingVo: NowShowingVo) {

        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("MOVIE", nowShowingVo)
        startActivity(intent)
    }


}
