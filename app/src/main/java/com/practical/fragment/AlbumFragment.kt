package com.practical.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.practical.R
import com.practical.adapter.AlbumAdapter
import com.practical.controller.CloseLoader
import com.practical.controller.OpenLoader
import com.practical.controller.ToastMessage
import com.practical.model.Album
import com.practical.model.AlbumResponse
import com.practical.presenter.AlbumPresenter
import com.practical.view.AlbumView
import kotlinx.android.synthetic.main.activity_genre_category.*
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment(var genre: String) : Fragment(), AlbumView, View.OnClickListener {

    lateinit var presenter: AlbumPresenter
    lateinit var adapter: AlbumAdapter
    var gridLayoutManager: GridLayoutManager? = null
    var arrayList = ArrayList<Album>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoad()
    }

    /**
     * Initialization
     */
    private fun initLoad() {
        gridLayoutManager = GridLayoutManager(context, 2)
        rvAlbum.layoutManager = gridLayoutManager
        adapter = AlbumAdapter(requireActivity(), arrayList)
        rvAlbum.adapter = adapter

        presenter = AlbumPresenter(this)
        presenter.getAlbumList(genre)
    }

    /**
     * Get Data from API
     */
    override fun successAlbumList(data: AlbumResponse) {
        adapter = AlbumAdapter(activity, data.albums.album)
        rvAlbum.adapter = adapter
    }

    /**
     * Show Progress
     */
    override fun showProgress() {
//        (context as Activity).OpenLoader()
    }

    /**
     * Hide Progress
     */
    override fun hideProgress() {
//        (context as Activity).CloseLoader()
    }

    /**
     * Error Handling
     */
    override fun error(e: Throwable) {
        (context as Activity).ToastMessage(getString(R.string.error))
    }

    override fun onClick(p0: View?) {

    }


}