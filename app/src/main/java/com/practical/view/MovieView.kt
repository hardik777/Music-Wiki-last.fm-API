package com.practical.view

import com.practical.model.MovieResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface MovieView {

    fun showProgress()

    fun hideProgress()

    fun successMovieList(data: MovieResponse)

    fun error(e: Throwable)

}