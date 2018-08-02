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
import com.example.soe_than.movietalkies.adapter.FavouriteRecyclerAdapter
import com.example.soe_than.movietalkies.adapter.NowShowingRecyclerAdapter
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.FavouriteViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.FavouriteViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import kotlinx.android.synthetic.main.fragment_now_showing.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouriteFragment : Fragment(), MovieDelegate {
    override fun onTapMovie(id: Int) {
        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("ID", id)
        intent.putExtra("TYPE", "favourite")
        startActivity(intent)
    }

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var viewModelFactory: FavouriteViewFactory
    lateinit var favouriteAdapter: FavouriteRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        viewModelFactory = InjectorUtils.provideFavouriteViewFactory(activity!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavouriteViewModel::class.java)

        setUpRecyclerView(view)

        getFavouriteMovies()




        return view
    }

    private fun setUpRecyclerView(view: View) {

        view.favouriteRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        favouriteAdapter = FavouriteRecyclerAdapter(context!!, this)
        view.favouriteRecyclerView.adapter = favouriteAdapter
    }

    private fun getFavouriteMovies() {

        viewModel.getFavouriteMovies().observe(activity!!, Observer { favouriteList ->
            if (favouriteList!!.size != 0 && favouriteList != null) {
//                view.nowProgress.visibility = View.GONE

                favouriteAdapter.setNewData(favouriteList as MutableList<FavouriteVo>)

            }
        })
    }

    override fun onResume() {
        super.onResume()
        getFavouriteMovies()
    }


}
