package com.practical.view

import com.practical.model.AlbumResponse
import com.practical.model.GenreCategoryResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface AlbumView {

    fun showProgress()

    fun hideProgress()

    fun successAlbumList(data: AlbumResponse)

    fun error(e: Throwable)

}