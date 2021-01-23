package com.practical.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.practical.R
import com.practical.adapter.AlbumAdapter
import com.practical.adapter.ArtistAdapter
import com.practical.controller.CloseLoader
import com.practical.controller.OpenLoader
import com.practical.controller.ToastMessage
import com.practical.model.Album
import com.practical.model.ArtistData
import com.practical.model.ArtistResponse
import com.practical.presenter.AlbumPresenter
import com.practical.presenter.ArtistPresenter
import com.practical.view.ArtistView
import kotlinx.android.synthetic.main.fragment_album.*

class ArtistFragment(var genre: String) : Fragment(), ArtistView, View.OnClickListener {

    lateinit var presenter: ArtistPresenter
    lateinit var adapter: ArtistAdapter
    var gridLayoutManager: GridLayoutManager? = null
    var arrayList = ArrayList<ArtistData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_artists, container, false)
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
        adapter = ArtistAdapter(requireActivity(), arrayList)
        rvAlbum.adapter = adapter

        presenter = ArtistPresenter(this)
        presenter.getArtistList(genre)
    }

    /**
     * Get Data from API
     */
    override fun successArtistList(data: ArtistResponse) {
        adapter = ArtistAdapter(activity, data.topartists.artist)
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