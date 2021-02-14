package com.example.soe_than.movietalkies.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.adapter.PopularRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.di.Injectable
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_popular.view.*

class PopularFragment : Fragment(), MovieDelegate, Injectable {

    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var popularAdapter: PopularRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popular, container, false)
//        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView(view)

        viewModel.getPopularMovies()

        viewModel.popularResultLiveData.observe(
            requireActivity(),
            Observer { popularList ->

                popularList!!.let {
                    view.popProgress.visibility = View.GONE
                    popularAdapter.setNewData(popularList as MutableList<PopularVo>)
                }
            }
        )
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.popularRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        popularAdapter = PopularRecyclerAdapter(requireContext(), this)
        view.popularRecyclerView.adapter = popularAdapter
    }

    override fun onTapMovie(id: Int) {
        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "popular")
        startActivity(intent)
    }
}
