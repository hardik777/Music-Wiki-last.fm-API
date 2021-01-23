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
import com.practical.adapter.ArtistAdapter
import com.practical.adapter.TracksAdapter
import com.practical.controller.CloseLoader
import com.practical.controller.OpenLoader
import com.practical.controller.ToastMessage
import com.practical.model.*
import com.practical.presenter.TrackPresenter
import com.practical.view.TrackView
import kotlinx.android.synthetic.main.fragment_album.*

class TracksFragment(var genre: String) : Fragment(), TrackView, View.OnClickListener {

    lateinit var presenter: TrackPresenter
    lateinit var adapter: TracksAdapter
    var gridLayoutManager: GridLayoutManager? = null
    var arrayList = ArrayList<TrackData>()

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
        adapter = TracksAdapter(requireActivity(), arrayList)
        rvAlbum.adapter = adapter

        presenter = TrackPresenter(this)
        presenter.getTrackList(genre)
    }

    /**
     * Get Data from API
     */
    override fun successTrackList(data: TrackResponse) {
        adapter = TracksAdapter(activity, data.tracks.track)
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