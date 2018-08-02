package com.example.soe_than.movietalkies.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.adapter.FavouriteRecyclerAdapter
import com.example.soe_than.movietalkies.adapter.SearchAdapter
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.example.soe_than.movietalkies.delegate.MovieDelegate
import com.example.soe_than.movietalkies.ui.ViewModel.FavouriteViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.FavouriteViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import kotlinx.android.synthetic.main.fragment_now_showing.view.*
import android.widget.Toast
import android.text.Editable


class SearchActivity : AppCompatActivity(), MovieDelegate {
    override fun onTapMovie(id: Int) {

    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory
    lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpRecyclerView()

        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView()

//        getSearchQuery()

        input_search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2:Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                getViewModelData(s.toString()!!)
            }
        })





    }

    private fun setUpRecyclerView() {

        searchRecyclerview.layoutManager = LinearLayoutManager(this)
        searchAdapter = SearchAdapter(this, this)
        searchRecyclerview.adapter = searchAdapter
    }


    private fun getSearchQuery() {

    }

    private fun getViewModelData(query:String)
    {
        viewModel.getSearchMovie(query).observe(this,
                Observer { searchList ->
                    if (searchList!!.size != 0 && searchList != null) {
                        searchProgress.visibility = View.GONE
                        searchAdapter.setNewData(searchList as MutableList<SearchVo>)
                    }
                })

    }


}
