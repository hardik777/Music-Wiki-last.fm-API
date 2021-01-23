package com.practical.view

import com.practical.model.GenreCategoryResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface GenreCategoryView {

    fun showProgress()

    fun hideProgress()

    fun successGenreCategoryList(data: GenreCategoryResponse)

    fun error(e: Throwable)

}