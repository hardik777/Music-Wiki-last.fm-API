package com.practical.presenter

import com.practical.APIclient.API
import com.practical.model.TrackResponse
import com.practical.view.TrackView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class TrackPresenter(internal var view: TrackView) {

    fun getTrackList(genre: String) {
        API()
            .getTrackList(genre)
            .getObjectObservable(TrackResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TrackResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: TrackResponse) {
                    view.successTrackList(data)
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