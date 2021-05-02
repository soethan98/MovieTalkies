package com.soethan.movietalkies.ui.fragment


import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.soethan.movietalkies.R
import com.soethan.movietalkies.adapter.FavouriteRecyclerAdapter
import com.soethan.movietalkies.data.Vo.FavouriteVo
import com.soethan.movietalkies.delegate.MovieDelegate
import com.soethan.movietalkies.di.Injectable
import com.soethan.movietalkies.ui.ViewModel.FavouriteViewModel
import com.soethan.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import javax.inject.Inject


class FavouriteFragment : Fragment(), MovieDelegate,Injectable {
    override fun onTapMovie(id: Int) {
        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "favourite")
        startActivity(intent)
    }

    private lateinit var viewModel: FavouriteViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var favouriteAdapter: FavouriteRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavouriteViewModel::class.java)

        setUpRecyclerView(view)

        getFavouriteMovies()




        return view
    }

    private fun setUpRecyclerView(view: View) {


        view.favouriteRecyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        favouriteAdapter = FavouriteRecyclerAdapter(requireContext(), this)
        view.favouriteRecyclerView.adapter = favouriteAdapter
    }

    private fun getFavouriteMovies() {

        viewModel.getFavouriteMovies().observe(requireActivity(), Observer { favouriteList ->

            favouriteList!!.let {

                favouriteAdapter.setNewData(favouriteList as MutableList<FavouriteVo>)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getFavouriteMovies()
    }


}
