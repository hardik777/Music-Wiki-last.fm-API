package com.practical.presenter

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.MovieResponse
import com.practical.view.MovieView
import com.practical.view.SearchView

/**
 * Created by Hardik(22/01/2021)
 * Get Search datd from API
 */
class SearchPresenter(internal var view: SearchView) {

    fun getSearchVideo(searchName: String) {
        API()
            .getSearchList(searchName)
            .getObjectObservable(MovieResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: MovieResponse) {
                    view.onSuccessSearchList(data)
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