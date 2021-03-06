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
import com.soethan.movietalkies.adapter.PopularRecyclerAdapter
import com.soethan.movietalkies.data.Vo.PopularVo
import com.soethan.movietalkies.delegate.MovieDelegate
import com.soethan.movietalkies.di.Injectable
import com.soethan.movietalkies.ui.ViewModel.MovieViewModel
import com.soethan.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import com.soethan.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_popular.view.*
import javax.inject.Inject


class PopularFragment : Fragment(), MovieDelegate,Injectable{

    private lateinit var viewModel: MovieViewModel

    @Inject
     lateinit var viewModelFactory: MainViewModelFactory
    lateinit var popularAdapter: PopularRecyclerAdapter




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popular, container, false)
//        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(activity!!)
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
        view.popularRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
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
