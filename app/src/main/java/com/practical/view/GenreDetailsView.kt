package com.practical.view

import com.practical.model.*

/**
 * Created by Hardik(22/01/2021)
 */
interface GenreDetailsView {

    fun showProgress()

    fun hideProgress()

    fun successGenreDetails(data: GenreDetailsResponse)

    fun error(e: Throwable)

}