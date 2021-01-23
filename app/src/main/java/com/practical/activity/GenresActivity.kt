package com.practical.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practical.R
import com.practical.adapter.GenreListAdapter
import com.practical.controller.CloseLoader
import com.practical.controller.NewIntentWithData
import com.practical.controller.OpenLoader
import com.practical.controller.ToastMessage
import com.practical.model.ModelMovies
import com.practical.model.Movie
import com.practical.model.MovieResponse
import com.practical.presenter.MoviePresenter
import com.practical.view.MovieView
import kotlinx.android.synthetic.main.activity_genres.*
import kotlinx.android.synthetic.main.search_dynamic.*

/**
 * Created by Hardik(22/01/2021)
 */
class GenresActivity : AppCompatActivity(), MovieView {

    lateinit var presenter: MoviePresenter
    lateinit var context: Context
    lateinit var adapter: GenreListAdapter
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayListMovie = ArrayList<ModelMovies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)

        initLoad()
    }

    /**
     * Initialization
     */
    private fun initLoad() {
        context = this

        linearLayoutManager = LinearLayoutManager(context)
        rvMovies.layoutManager = linearLayoutManager
        adapter = GenreListAdapter(this, arrayListMovie)
        rvMovies.adapter = adapter

        presenter = MoviePresenter(this)
        presenter.getAllVideo()
    }

    /**
     * Show Progress
     */
    override fun showProgress() {
        (context as Activity).OpenLoader()
    }

    /**
     * Hide Progress
     */
    override fun hideProgress() {
        (context as Activity).CloseLoader()
    }

    /**
     * Get Data from API
     */
    override fun successMovieList(data: MovieResponse) {
        Log.d("RESPONSE", Gson().toJson(data))

        arrayListMovie = ArrayList()

        if (data.movies.isNotEmpty()) {
            val arrayMovieData = data.movies as ArrayList<Movie>

            for (item in arrayMovieData) {
                val genres = item.genres

                for (itemGenre in genres) {

                    var flag = true

                    for (i in arrayListMovie.indices) {
                        if (arrayListMovie[i].genre == itemGenre) {

                            var tempArray: ArrayList<Movie> = ArrayList()
                            tempArray = arrayListMovie[i].childItem
                            tempArray.add(item)

                            flag = false
                        }
                    }

                    if (flag) {
                        var tempArray: ArrayList<Movie> = ArrayList()
                        tempArray.add(item)
                        arrayListMovie.add(ModelMovies(itemGenre, tempArray))
                    }

                }
            }

            adapter = GenreListAdapter(this, arrayListMovie)
            rvMovies.adapter = adapter
        }
    }

    /**
     * Error Handling
     */
    override fun error(e: Throwable) {
        (context as Activity).ToastMessage(getString(R.string.error))
    }

}