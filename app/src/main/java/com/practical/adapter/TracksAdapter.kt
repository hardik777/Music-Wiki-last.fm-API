package com.practical.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.practical.R
import com.practical.activity.AlbumDetailsActivity
import com.practical.activity.TrackDetailsActivity
import com.practical.controller.LoadImage
import com.practical.controller.NewIntentWithData
import com.practical.model.ArtistData
import com.practical.model.ModelMovies
import com.practical.model.TrackData
import com.practical.model.TrackDetailsResponse
import kotlinx.android.synthetic.main.raw_album.view.*

/**
 * Created by Hardik(22/01/2021)
 */
class TracksAdapter(
    var activity: Activity?,
    private var arrayList: List<TrackData>
) : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View? = null

        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_track, parent, false)

        return ViewHolder(v!!)
    }

    /**
     * Get total item Count
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Set data in views
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = arrayList[position]

        activity?.LoadImage(data.image[data.image.size - 1].text, holder.imgMovie)

        holder.tvAlbumName.text = data.name
        holder.tvArtist.text = data.artist.name

//        val linearLayoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        holder.itemView.rvSub.layoutManager = linearLayoutManager
//
//        holder.itemView.rvSub.apply {
//            layoutManager = linearLayoutManager
//            adapter = MovieListAdapter(activity, arrayList[position].childItem)
//            setRecycledViewPool(viewPool)
//        }

        val hashMap = HashMap<String, Any>()
        hashMap["track"] = Gson().toJson(data).toString()

        holder.itemView.setOnClickListener {
            activity?.NewIntentWithData(
                TrackDetailsActivity::class.java,
                hashMap,
                false,
                false
            )
        }
    }

    /**
     * declare views
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgMovie = view.imgMovie
        var tvAlbumName = view.tvAlbumName
        var tvArtist = view.tvArtist
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}