package com.practical.view

import com.practical.model.MovieResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface SearchView {

    fun showProgress()

    fun hideProgress()

    fun onSuccessSearchList(data: MovieResponse)

    fun error(e: Throwable)

}