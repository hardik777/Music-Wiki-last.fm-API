package com.practical.adapter

import android.app.Activity
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.practical.R
import com.practical.activity.AlbumDetailsActivity
import com.practical.activity.ArtistsDetailsActivity
import com.practical.controller.LoadImage
import com.practical.controller.NewIntentWithData
import com.practical.model.ArtistData
import kotlinx.android.synthetic.main.raw_album.view.*

/**
 * Created by Hardik(22/01/2021)
 */
class ArtistAdapter(
    var activity: Activity?,
    private var arrayList: List<ArtistData>
) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View? = null

        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_artist, parent, false)

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

        holder.tvAlbumName.visibility = View.GONE
        holder.tvArtist.text = data.name

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
        hashMap["artist"] = Gson().toJson(data).toString()

        holder.itemView.setOnClickListener {
            activity?.NewIntentWithData(
                ArtistsDetailsActivity::class.java,
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