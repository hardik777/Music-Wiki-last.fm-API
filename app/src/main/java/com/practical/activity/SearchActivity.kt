package com.practical.activity

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practical.R
import com.practical.adapter.SearchListAdapter
import com.practical.controller.ToastMessage
import com.practical.model.Movie
import com.practical.model.MovieResponse
import com.practical.presenter.SearchPresenter
import com.practical.view.SearchView
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_dynamic.*

/**
 * Created by Hardik(22/01/2021)
 */
class SearchActivity : AppCompatActivity(), SearchView, View.OnClickListener {
    lateinit var context: Activity
    lateinit var presenter: SearchPresenter
    lateinit var adapter: SearchListAdapter
    var arrayListMovie = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        context = this

        initLoad()
    }

    /**
     * Initialization
     */
    private fun initLoad() {

        presenter = SearchPresenter(this)

        imgClearSearch.setOnClickListener(this)

        arrayListMovie = ArrayList()
        val linearLayoutManager = LinearLayoutManager(context)
        rvSearch.layoutManager = linearLayoutManager
        adapter = SearchListAdapter(this, arrayListMovie)
        rvSearch.adapter = adapter

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(search: Editable?) {
                if (TextUtils.isEmpty(search.toString())) {
                    imgClearSearch.visibility = View.GONE
                    //return
                } else {
                    imgClearSearch.visibility = View.VISIBLE
                }
                val getSearchText = search.toString().trim()

                presenter.getSearchVideo(getSearchText)
            }

            override fun beforeTextChanged(
                    search: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
            ) {
            }

            override fun onTextChanged(search: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    /**
     * Click events of views
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imgClearSearch -> {
                txtSearch.clearFocus()
                txtSearch.setText("")

                arrayListMovie = ArrayList()
                arrayListMovie.clear()
                adapter = SearchListAdapter(this, arrayListMovie)
                rvSearch.adapter = adapter
            }
        }
    }

    /**
     * Show Progress
     */
    override fun showProgress() {
        //(context as Activity).OpenLoader()
    }

    /**
     * Hide Progress
     */
    override fun hideProgress() {
        //(context as Activity).CloseLoader()
    }

    /**
     * Get Data from API
     */
    override fun onSuccessSearchList(data: MovieResponse) {

        Log.d("RESPONSE", Gson().toJson(data))

        arrayListMovie = data.movies as ArrayList<Movie>

        adapter = SearchListAdapter(this, arrayListMovie)
        rvSearch.adapter = adapter
    }

    /**
     * Error Handling
     */
    override fun error(e: Throwable) {
        (context as Activity).ToastMessage(getString(R.string.error))
    }
}