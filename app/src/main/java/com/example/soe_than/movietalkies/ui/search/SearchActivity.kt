package com.example.soe_than.movietalkies.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.adapter.SearchAdapter
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*
import android.view.View
import com.example.soe_than.movietalkies.delegate.SearchDelegate
import com.example.soe_than.movietalkies.ui.detail.SearchDetailActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


class SearchActivity : AppCompatActivity(), SearchDelegate {
    override fun onTapSearchResult(searchVo: SearchVo) {
        val intent = Intent(this, SearchDetailActivity::class.java)
        intent.putExtra("Search", searchVo.id)
        startActivity(intent)
    }


    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory
    lateinit var searchAdapter: SearchAdapter

    lateinit var disposable: CompositeDisposable


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpRecyclerView()

        disposable = CompositeDisposable()

        viewModelFactory = InjectorUtils.provideMovieViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)

        setUpRecyclerView()
        observeSearchView()

        viewModel.searchResultLiveData.observe(this,
                Observer { searchList ->
                    if (searchList!!.isNotEmpty()) {
                        searchAdapter.setNewData(searchList as MutableList<SearchVo>)
                        search_progress.visibility = View.GONE
                    }
                })





    }

    private fun setUpRecyclerView() {

        searchRecyclerview.layoutManager = LinearLayoutManager(this)
        searchAdapter = SearchAdapter(this, this)
        searchRecyclerview.adapter = searchAdapter
    }





    private fun observeSearchView() {
        disposable.add(RxTextView.textChangeEvents(input_search)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter {
                    it.text().isNotEmpty()
                }

                .distinctUntilChanged().switchMap { searchKey ->
                    Observable.just(searchKey)
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe { searchString ->
                    viewModel.getSearchList(searchString.text().toString())
                }
        )
    }

}
