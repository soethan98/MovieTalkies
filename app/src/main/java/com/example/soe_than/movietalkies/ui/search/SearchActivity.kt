package com.example.soe_than.movietalkies.ui.search

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.adapter.SearchAdapter
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.activity_search.*
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.delegate.SearchDelegate
import com.example.soe_than.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import com.example.soe_than.movietalkies.ui.detail.SearchDetailActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchActivity : AppCompatActivity(), SearchDelegate,HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>




    override fun onTapSearchResult(searchVo: SearchVo) {
        val intent = Intent(this, SearchDetailActivity::class.java)
        intent.putExtra("Search", searchVo.id)
        startActivity(intent)
    }


    private lateinit var viewModel: MovieViewModel

    @Inject
     lateinit var viewModelFactory: ViewModelProvider.Factory

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

        searchRecyclerview.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
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

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }


}
