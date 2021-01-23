package com.practical.view

import com.practical.model.AlbumDetailsResponse
import com.practical.model.GenreCategoryResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface AlbumDetailsView {

    fun showProgress()

    fun hideProgress()

    fun successAlbumDetails(data: AlbumDetailsResponse)

    fun error(e: Throwable)

}