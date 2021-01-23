package com.practical.presenter

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.MovieResponse
import com.practical.view.MovieView

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class MoviePresenter(internal var view: MovieView) {

    fun getAllVideo() {
        API()
            .getMovieList()
            .getObjectObservable(MovieResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: MovieResponse) {
                    view.successMovieList(data)
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