package com.example.soe_than.movietalkies.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.adapter.NowShowingRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.di.Injectable
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_now_showing.view.*

class NowShowingFragment : Fragment(), MovieDelegate, Injectable {

    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var nowShowingAdapter: NowShowingRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_now_showing, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getNowShowingMovies()

        viewModel.nowShowingResultLiveData.observe(
            requireActivity(),
            Observer { nowshowingList ->

                nowshowingList!!.let {
                    view.nowProgress.visibility = View.GONE

                    nowShowingAdapter.setNewData(nowshowingList as MutableList<NowShowingVo>)
                }
            }
        )
        return view
    }

    private fun setUpRecyclerView(view: View) {

        view.nowShowingRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        nowShowingAdapter = NowShowingRecyclerAdapter(requireContext(), this)
        view.nowShowingRecyclerView.adapter = nowShowingAdapter
    }

    override fun onTapMovie(id: Int) {

        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "nowshowing")
        startActivity(intent)
    }
}
