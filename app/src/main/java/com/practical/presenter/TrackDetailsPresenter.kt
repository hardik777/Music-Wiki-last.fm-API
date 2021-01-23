package com.practical.presenter

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.TrackDetailsResponse
import com.practical.model.MovieResponse
import com.practical.view.AlbumDetailsView
import com.practical.view.GenreCategoryView
import com.practical.view.MovieView
import com.practical.view.TrackDetailsView

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class TrackDetailsPresenter(internal var view: TrackDetailsView) {

    fun getTrackDetail(artist: String, track: String) {
        API()
            .getTrackDetail(artist, track)
            .getObjectObservable(TrackDetailsResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TrackDetailsResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: TrackDetailsResponse) {
                    view.successTrackDetails(data)
                }

                override fun onError(e: Throwable) {
                    view.error(e)
                    view.hideProgress()
                }

                override fun onComplete() {
                    view.hideProgress()
                }
            })
    }

}