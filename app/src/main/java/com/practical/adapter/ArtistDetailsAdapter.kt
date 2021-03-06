package com.practical.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.practical.R
import com.practical.activity.GenreDetailsActivity
import com.practical.controller.NewIntentWithData
import com.practical.model.TagArtist
import kotlinx.android.synthetic.main.raw_genre.view.*

/**
 * Created by Hardik(22/01/2021)
 */
class ArtistDetailsAdapter(
    var activity: Activity,
    private var arrayList: List<TagArtist>
) : RecyclerView.Adapter<ArtistDetailsAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View? = null

        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_tag_artist_details, parent, false)

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

        holder.tvGenre.text = data.name

        val hashMap = HashMap<String, Any>()
        hashMap["genre"] = Gson().toJson(data).toString()

        holder.itemView.setOnClickListener {
            activity.NewIntentWithData(
                GenreDetailsActivity::class.java,
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
        var tvGenre = view.tvGenre
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}