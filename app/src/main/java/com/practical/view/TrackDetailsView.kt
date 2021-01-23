package com.practical.view

import com.practical.model.AlbumDetailsResponse
import com.practical.model.GenreCategoryResponse
import com.practical.model.TrackDetailsResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface TrackDetailsView {

    fun showProgress()

    fun hideProgress()

    fun successTrackDetails(data: TrackDetailsResponse)

    fun error(e: Throwable)

}